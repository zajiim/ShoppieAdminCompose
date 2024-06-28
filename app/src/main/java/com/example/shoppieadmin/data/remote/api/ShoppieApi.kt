package com.example.shoppieadmin.data.remote.api

import com.example.shoppieadmin.domain.add_products.models.AddProduct
import com.example.shoppieadmin.domain.add_products.models.AddProductsResponse
import com.example.shoppieadmin.domain.auth.login.models.LoginRequest
import com.example.shoppieadmin.domain.auth.login.models.LoginResponse
import com.example.shoppieadmin.domain.home.models.AllProductsResponse
import com.example.shoppieadmin.domain.main.models.TokenValidationResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

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

    @GET("api/admin/get-all-products")
    suspend fun getAllProducts(
        @Header("x-auth-token") token: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): AllProductsResponse
}