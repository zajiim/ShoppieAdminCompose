package com.example.shoppieadmin.domain.auth.login.models


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("address")
    val address: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("__v")
    val v: Int
)