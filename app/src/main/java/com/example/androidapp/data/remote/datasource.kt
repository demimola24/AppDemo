package com.example.androidapp.data.remote

import com.example.androidapp.data.models.UserData
import com.example.androidapp.utils.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class StudentDataSource
@Inject constructor(private val apiService: StudentService) : StudentRepository, BaseRepository() {

    override suspend fun fetchStudents(): Flow<DataResult<List<UserData>>> {
        return flow {
            emit(processApiCall { apiService.fetchStudents() })
        }.flowOn(Dispatchers.IO)
    }

}