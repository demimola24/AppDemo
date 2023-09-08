package com.example.androidapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidapp.R
import com.example.androidapp.data.models.UserData
import com.example.androidapp.utils.GlideApp
import com.google.android.material.imageview.ShapeableImageView

class ViewAdapter : ListAdapter<UserData, RecyclerView.ViewHolder>(UserCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ODD_ITEM_VIEW -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.odd_item_layout, parent, false)
                ListenItemViewHolder(view)
            }

            EVEN_PRODUCT_VIEW -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.even_item_layout, parent, false)
                ListenItemViewHolder(view)
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as ListenItemViewHolder).bind(item)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) EVEN_PRODUCT_VIEW else ODD_ITEM_VIEW
    }


    inner class ListenItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTv: TextView = itemView.findViewById(R.id.tv_profile_name)
        private val ageTv: TextView = itemView.findViewById(R.id.tv_profile_age)
        private val classTv: TextView = itemView.findViewById(R.id.tv_class)
        private val classNumber: TextView = itemView.findViewById(R.id.tv_reg_number)
        private val imageIv: ShapeableImageView = itemView.findViewById(R.id.im_profile_image)

        fun bind(userData: UserData) {
            nameTv.text = userData.name
            ageTv.text = itemView.context.getString(R.string.age_string, userData.age.toString())
            classTv.text = userData.department
            classNumber.text = userData.profileId.uppercase()
            GlideApp.with(itemView.context).load(userData.avatar).into(imageIv)
        }
    }

    class UserCallBack : DiffUtil.ItemCallback<UserData>() {
        override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        const val ODD_ITEM_VIEW = R.layout.odd_item_layout
        const val EVEN_PRODUCT_VIEW = R.layout.even_item_layout

    }

}