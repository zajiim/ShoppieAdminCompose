package com.example.shoppieadmin.presentation.home.add_products

data class AddProductsState(
    val nameInput: String = "",
    val descriptionInput: String = "",
    val price: String = "",
    val quantity: String = "",
    val productCategory: String = "",
    val isLoading: Boolean = false,
    val isSuccessfullyUploaded: Boolean = false
)
