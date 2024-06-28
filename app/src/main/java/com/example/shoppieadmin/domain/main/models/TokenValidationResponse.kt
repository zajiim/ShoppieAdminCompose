package com.example.shoppieadmin.domain.main.models


import com.google.gson.annotations.SerializedName

data class TokenValidationResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)