package com.example.shoppieadmin.presentation.auth.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieadmin.data.remote.ShoppieApi
import com.example.shoppieadmin.domain.auth.login.models.LoginRequest
import com.example.shoppieadmin.domain.auth.main.use_cases.TokenUseCases
import com.example.shoppieadmin.domain.auth.login.use_cases.ValidationUseCases
import com.example.shoppieadmin.presentation.auth.login.validation.LoginEmailValidationType
import com.example.shoppieadmin.presentation.auth.login.validation.LoginPasswordValidationType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

const val TAG = "LoginViewModel"

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validationUseCases: ValidationUseCases,
    private val shoppieRepo: ShoppieApi,
    private val tokenUseCases: TokenUseCases
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


    fun onLoginClick() {
        loginState.value = loginState.value.copy(
            isLoading = true
        )

        viewModelScope.launch {

            loginState.value = try {

                val loginResult = shoppieRepo.logIn(
                    LoginRequest(
                        email = loginState.value.emailInput,
                        password = loginState.value.passwordInput
                    )
                )

                Log.e(TAG, loginResult.token)

                tokenUseCases.saveTokenUseCase(loginResult.token)

                loginState.value.copy(
                    isSuccessfullyLoggedIn = true,
                    isLoading = false,
                    afterSuccessfullyLoggedIn = loginResult.token
                )
            } catch (e: HttpException) {
                if (e.code() == 400) {
                    loginState.value.copy(
                        errorMsgLoginProcess = e.message(),
                        isLoading = false
                    )
                } else {
                    Log.e(TAG, "onLoginClick: errorr >>>>>>> ${e.message()}", )
                    loginState.value.copy(
                        errorMsgLoginProcess = e.message.toString()
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                loginState.value.copy(
                    errorMsgLoginProcess = e.message
                )
            } finally {
                loginState.value.copy(
                    isLoading = false
                )
            }
        }
    }


}