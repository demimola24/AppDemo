package com.example.androidapp

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.androidapp.adapter.ViewAdapter
import com.example.androidapp.utils.Result
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter : ViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false);

        val recyclerView =  findViewById<RecyclerView>(R.id.recyclerView)
        val progressView =  findViewById<ProgressBar>(R.id.progress)
        adapter = ViewAdapter()
        recyclerView.adapter = adapter
        viewModel.fetchStudents()

        with(viewModel) {
            responseLiveData.observe(this@MainActivity, Observer { response ->
                when (response) {
                    is Result.Success -> {
                        progressView.visibility = View.GONE

                        response.data?.let {
                            adapter.submitList(it)
                        }
                    }

                    is Result.Error -> {
                        progressView.visibility = View.GONE
                        Toast.makeText(
                            this@MainActivity,
                            response.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is Result.Loading -> {
                        progressView.visibility = View.VISIBLE
                    }
                }
            })
        }

    }
}