package com.example.androidapp.data.models

data class UserData(
    val age: Int,
    val avatar: String,
    val createdAt: String,
    val department: String,
    val id: String,
    val name: String,
    val profileId: String


) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}