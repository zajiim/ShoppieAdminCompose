package com.example.shoppieadmin.data.remote

import com.example.shoppieadmin.domain.auth.login.models.LoginRequest
import com.example.shoppieadmin.domain.auth.login.models.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ShoppieApi {
    @POST("api/admin/signin")
    suspend fun logIn(
        @Body logInRequest: LoginRequest
    ): LoginResponse
}