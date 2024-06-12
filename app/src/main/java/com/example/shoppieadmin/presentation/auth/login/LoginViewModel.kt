package com.example.shoppieadmin.presentation.auth.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.shoppieadmin.domain.auth.login.use_cases.ValidationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.log


@HiltViewModel
class LoginViewModel @Inject constructor(
//    private val validationUseCases: ValidationUseCases
): ViewModel() {

    private val _loginState: MutableState<LoginState> = mutableStateOf(LoginState())
    val loginState = _loginState

    fun onEmailChange(newValue: String) {
        loginState.value = loginState.value.copy(
            emailInput = newValue
        )
    }

    fun onPasswordChange(newValue: String) {
        loginState.value = loginState.value.copy(
            passwordInput = newValue
        )
    }

    fun onTogglePasswordVisibility() {
        loginState.value = loginState.value.copy(
            isPasswordShown = !loginState.value.isPasswordShown
        )
    }




}