package com.example.androidapp

import androidx.lifecycle.*
import com.example.androidapp.data.models.UserData
import com.example.androidapp.data.remote.StudentDataSource
import com.example.androidapp.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataSource: StudentDataSource
) : ViewModel()  {

    private val _responseLiveData: MutableLiveData<Result<List<UserData>>> = MutableLiveData()
    val responseLiveData: LiveData<Result<List<UserData>>> = _responseLiveData


    fun fetchStudents() = viewModelScope.launch {
        dataSource.fetchStudents().collect { values ->
            _responseLiveData.value = values
        }
    }

}