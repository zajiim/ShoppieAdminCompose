package com.example.shoppieadmin.data.repository

import com.example.shoppieadmin.data.remote.ShoppieApi
import com.example.shoppieadmin.domain.auth.login.models.LoginRequest
import com.example.shoppieadmin.domain.auth.login.models.LoginResponse
import com.example.shoppieadmin.domain.auth.login.repository.ShoppieRepo
import com.example.shoppieadmin.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ShoppieRepoImpl @Inject constructor(
    private val shoppieApi: ShoppieApi
): ShoppieRepo {
    override fun logIn(email: String, password: String): Flow<Resource<LoginResponse>> = flow {

        try {
            val response = shoppieApi.logIn(
                LoginRequest(
                    email,
                    password
                )
            )
            emit(Resource.Success(data = response))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }

    }
}