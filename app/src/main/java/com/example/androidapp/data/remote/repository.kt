package com.example.androidapp.data.remote

import com.example.androidapp.data.models.UserData
import com.example.androidapp.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class StudentRepository
@Inject constructor(private val apiService: StudentService) : StudentDataSource, BaseRepository()   {

    override suspend fun fetchStudents(): Flow<Result<List<UserData>>> {
        return flow {
            emit(processApiCall { apiService.fetchStudents() })
        }.flowOn(Dispatchers.IO)
    }

}