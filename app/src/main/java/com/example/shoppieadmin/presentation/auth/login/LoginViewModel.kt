package com.example.shoppieadmin.presentation.auth.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.shoppieadmin.domain.auth.login.use_cases.ValidationUseCases
import com.example.shoppieadmin.presentation.auth.login.validation.LoginEmailValidationType
import com.example.shoppieadmin.presentation.auth.login.validation.LoginPasswordValidationType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validationUseCases: ValidationUseCases,
) : ViewModel() {

    private val _loginState: MutableState<LoginState> = mutableStateOf(LoginState())
    val loginState = _loginState

    fun onEmailChange(newValue: String) {
        loginState.value = loginState.value.copy(
            emailInput = newValue
        )
        checkEmailInputValidation()
    }

    private fun checkEmailInputValidation() {
        val emailValidationResult =
            validationUseCases.validationEmailUseCase(email = loginState.value.emailInput)
        processEmailInputValidationType(emailValidationResult)
    }


    private fun processEmailInputValidationType(emailValidationResult: LoginEmailValidationType) {
        loginState.value = when (emailValidationResult) {
            LoginEmailValidationType.EMPTY_EMAIL -> {
                loginState.value.copy(
                    emailErrorMsgInput = "Please enter an email",
                    isInputValid = false
                )
            }

            LoginEmailValidationType.INVALID_EMAIL -> {
                loginState.value.copy(
                    emailErrorMsgInput = "Please enter a valid email",
                    isInputValid = false
                )
            }

            LoginEmailValidationType.VALID_EMAIL -> {
                loginState.value.copy(
                    emailErrorMsgInput = null,
                    isInputValid = true
                )
            }
        }

    }

    fun onPasswordChange(newValue: String) {
        loginState.value = loginState.value.copy(
            passwordInput = newValue
        )
        checkPasswordInputValidation()
    }


    private fun checkPasswordInputValidation() {
        val passwordValidationResult =
            validationUseCases.validationPasswordUseCase(password = loginState.value.passwordInput)
        processPasswordInputValidationType(passwordValidationResult)
    }

    private fun processPasswordInputValidationType(passwordValidationResult: LoginPasswordValidationType) {
        loginState.value = when (passwordValidationResult) {
            LoginPasswordValidationType.EMPTY_PASSWORD -> {
                loginState.value.copy(
                    passwordErrorMsgInput = "Please enter a password",
                    isInputValid = false
                )
            }

            LoginPasswordValidationType.INVALID_PASSWORD -> {
                loginState.value.copy(
                    passwordErrorMsgInput = "Please enter a valid password",
                    isInputValid = false
                )
            }

            LoginPasswordValidationType.VALID_PASSWORD -> {
                loginState.value.copy(
                    passwordErrorMsgInput = null,
                    isInputValid = true
                )
            }
        }

    }

    fun onTogglePasswordVisibility() {
        loginState.value = loginState.value.copy(
            isPasswordShown = !loginState.value.isPasswordShown
        )
    }


}