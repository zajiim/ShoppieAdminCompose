package com.example.shoppieadmin.data.repository

import com.example.shoppieadmin.data.remote.api.ShoppieApi
import com.example.shoppieadmin.domain.add_products.models.AddProduct
import com.example.shoppieadmin.domain.add_products.models.AddProductsResponse
import com.example.shoppieadmin.domain.auth.login.models.LoginRequest
import com.example.shoppieadmin.domain.auth.login.models.LoginResponse
import com.example.shoppieadmin.domain.auth.login.repository.ShoppieRepo
import com.example.shoppieadmin.domain.home.models.AllProductsResponse
import com.example.shoppieadmin.domain.main.models.TokenValidationResponse
import com.example.shoppieadmin.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ShoppieRepoImpl @Inject constructor(
    private val shoppieApi: ShoppieApi
) : ShoppieRepo {
    override fun logIn(email: String, password: String): Flow<Resource<LoginResponse>> = flow {

        try {
            val response = shoppieApi.logIn(
                LoginRequest(
                    email, password
                )
            )
            emit(Resource.Success(data = response))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }

    }

    override fun isTokenValid(token: String): Flow<Resource<TokenValidationResponse>> = flow {
        try {
            val response = shoppieApi.isTokenValid(token)
            if (response.status) {
                emit(Resource.Success(data = response))
            } else {
                emit(Resource.Error(message = response.message))
            }
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override fun addProducts(
        token: String, addProduct: AddProduct
    ): Flow<Resource<AddProductsResponse>> = flow {
        try {
            val response = shoppieApi.uploadProduct(
                token, addProduct
            )
            emit(Resource.Success(data = response))

        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))

        }

    }

}