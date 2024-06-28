package com.example.shoppieadmin.presentation.home.add_products

data class AddProductsState(
    val nameInput: String = "",
    val descriptionInput: String = "",
    val price: String = "",
    val quantity: String = "",
    val productCategory: String = "",
    val productImages: List<String> = listOf(),
    val isLoading: Boolean = false,
    val isSuccessfullyUploaded: Boolean = false,
    val errorFound: String? = null,
    val navigateToBack: Boolean = false,
)
