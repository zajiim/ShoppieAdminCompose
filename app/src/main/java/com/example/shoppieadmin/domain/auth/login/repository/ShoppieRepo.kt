package com.example.shoppieadmin.domain.auth.login.repository

import com.example.shoppieadmin.domain.auth.add_products.models.AddProduct
import com.example.shoppieadmin.domain.auth.add_products.models.AddProductsResponse
import com.example.shoppieadmin.domain.auth.login.models.LoginResponse
import com.example.shoppieadmin.domain.auth.main.models.TokenValidationResponse
import com.example.shoppieadmin.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ShoppieRepo {
    fun logIn(email: String, password: String): Flow<Resource<LoginResponse>>

    fun isTokenValid(token: String): Flow<Resource<TokenValidationResponse>>

    fun addProducts(
        token: String,
        addProduct: AddProduct
        ): Flow<Resource<AddProductsResponse>>
}