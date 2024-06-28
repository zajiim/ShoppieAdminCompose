package com.example.shoppieadmin.domain.add_products.models

data class AddProduct(
    val name: String,
    val description: String,
    val price: Int,
    val quantity: Int,
    val category: String,
    val images: List<String> = listOf(),
)
