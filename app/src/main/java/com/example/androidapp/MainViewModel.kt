package com.example.androidapp

import androidx.lifecycle.*
import com.example.androidapp.data.models.UserData
import com.example.androidapp.data.remote.StudentRepository
import com.example.androidapp.utils.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: StudentRepository
) : ViewModel()  {

    private val _responseLiveData: MutableLiveData<DataResult<List<UserData>>> = MutableLiveData()
    val responseLiveData: LiveData<DataResult<List<UserData>>> = _responseLiveData


    fun fetchStudents(){
        viewModelScope.launch {
            repository.fetchStudents().collect { values ->
                _responseLiveData.value = values
            }
        }
    }

}