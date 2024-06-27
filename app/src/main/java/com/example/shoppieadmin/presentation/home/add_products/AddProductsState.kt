package com.example.shoppieadmin.presentation.home.add_products

data class AddProductsState(
    val nameInput: String = "",
    val descriptionInput: String = "",
    val price: Int = 0,
    val quantity: Int = 0,
    val productCategory: String = "",
    val isLoading: Boolean = false,
    val isSuccessfullyUploaded: Boolean = false
)
