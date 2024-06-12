package com.example.shoppieadmin.presentation.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppieadmin.R
import com.example.shoppieadmin.presentation.auth.login.components.CustomButton
import com.example.shoppieadmin.presentation.auth.login.components.CustomTextField
import com.example.shoppieadmin.presentation.auth.login.components.LoginContainer
import com.example.shoppieadmin.ui.theme.Orange

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "logo"
        )

        LoginContainer(
            emailValue = { loginViewModel.loginState.value.emailInput },
            passwordValue = { loginViewModel.loginState.value.passwordInput },
            onEmailChanged = loginViewModel::onEmailChange,
            onPasswordChanged = loginViewModel::onPasswordChange,
            buttonEnabled = { loginViewModel.loginState.value.isInputValid },
            onLoginClick = { /*TODO*/ },
            isPasswordVisible = { loginViewModel.loginState.value.isPasswordShown },
            onTrailingIconClick = loginViewModel::onTogglePasswordVisibility,
            onEmailErrorHint = { loginViewModel.loginState.value.emailErrorMsgInput },
            onPasswordErrorHint = { loginViewModel.loginState.value.passwordErrorMsgInput },
            isLoading = { loginViewModel.loginState.value.isLoading },
            buttonBackgroundColor = Orange,
            buttonTextColor = Color.White,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
        )

    }


}