package com.example.androidapp.data.remote

import com.example.androidapp.data.models.UserData
import retrofit2.Response
import retrofit2.http.*

interface StudentService {
    @GET("api/v1/students")
    suspend fun fetchStudents(): Response<List<UserData>>
}
