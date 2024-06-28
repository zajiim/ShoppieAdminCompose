package com.example.shoppieadmin.domain.home.models


import com.google.gson.annotations.SerializedName

data class AllProductsResponse(
    @SerializedName("currentPage")
    val currentPage: Int,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalPages")
    val totalPages: Int,
    @SerializedName("totalProducts")
    val totalProducts: Int
)