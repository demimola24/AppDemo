package com.example.androidapp.data.remote

import com.example.androidapp.data.models.UserData
import com.example.androidapp.utils.Result
import kotlinx.coroutines.flow.Flow

interface StudentDataSource {
    suspend fun fetchStudents(): Flow<Result<List<UserData>>>
}