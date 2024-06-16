package com.example.shoppieadmin.domain.auth.login.repository

import com.example.shoppieadmin.domain.auth.login.models.LoginResponse
import com.example.shoppieadmin.domain.auth.main.models.TokenValidationResponse
import com.example.shoppieadmin.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ShoppieRepo {
    fun logIn(email: String, password: String): Flow<Resource<LoginResponse>>

    fun isTokenValid(token: String): Flow<Resource<TokenValidationResponse>>
}