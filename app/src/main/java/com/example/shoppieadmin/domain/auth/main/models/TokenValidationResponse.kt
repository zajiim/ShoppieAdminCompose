package com.example.shoppieadmin.domain.auth.main.models


import com.google.gson.annotations.SerializedName

data class TokenValidationResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)