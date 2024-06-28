package com.example.shoppieadmin.data.remote

import com.example.shoppieadmin.domain.auth.add_products.models.AddProduct
import com.example.shoppieadmin.domain.auth.add_products.models.AddProductsResponse
import com.example.shoppieadmin.domain.auth.login.models.LoginRequest
import com.example.shoppieadmin.domain.auth.login.models.LoginResponse
import com.example.shoppieadmin.domain.auth.main.models.TokenValidationResponse
import com.example.shoppieadmin.utils.Resource
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ShoppieApi {
    @POST("api/admin/signin")
    suspend fun logIn(
        @Body logInRequest: LoginRequest
    ): LoginResponse

    @POST("api/admin/tokenIsValid")
    suspend fun isTokenValid(
        @Header("x-auth-token") token: String,
//        @Header("Content-Type") contentType: String = "application/json"
    ): TokenValidationResponse

    @POST("api/admin/add-products")
    suspend fun uploadProduct(
        @Header("x-auth-token") token: String,
        @Body addProduct: AddProduct
    ): AddProductsResponse
}