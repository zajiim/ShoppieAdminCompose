package com.example.shoppieadmin.presentation.auth.login

data class LoginState(
    val emailInput: String = "",
    val passwordInput: String = "",
    val isInputValid: Boolean = false,
    val isPasswordShown: Boolean = false,
    val errorMsgInput: String? = null,
    val isLoading: Boolean = false,
    val isSuccessfullyLoggedIn: Boolean = false,
    val afterSuccessfullyLoggedIn: String? = null,
    val errorMsgLoginProcess: String? = null
)
