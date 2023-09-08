package com.example.androidapp.data.remote

import com.example.androidapp.utils.Result
import retrofit2.Response

abstract class BaseRepository {

    suspend fun <T> processApiCall(apiCall: suspend () -> Response<T>): Result<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return Result.Success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String): Result<T> =
        Result.Error("Call failed because:  $errorMessage")
}