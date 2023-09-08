package com.example.androidapp.data.remote

import com.example.androidapp.data.models.UserData
import com.example.androidapp.utils.DataResult
import kotlinx.coroutines.flow.Flow

interface StudentRepository {
    suspend fun fetchStudents(): Flow<DataResult<List<UserData>>>
}