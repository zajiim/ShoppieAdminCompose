package com.example.shoppieadmin.presentation.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.shoppieadmin.R
import com.example.shoppieadmin.core.navigation.Graph
import com.example.shoppieadmin.presentation.auth.login.components.LoginContainer
import com.example.shoppieadmin.ui.theme.Orange
import com.example.shoppieadmin.ui.theme.PrimaryColor

@Composable
fun LoginScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {

    val loginState by loginViewModel.loginState

    if (loginState.navigateToMain) {
        LaunchedEffect(key1 = Unit) {
            navController.navigate(Graph.MAIN) {
                popUpTo(Graph.AUTH) { inclusive = true }
            }
            loginViewModel.onNavigatedToMain()
        }
    }

    Column(
        modifier = modifier.fillMaxSize().background(PrimaryColor),
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
            onLoginClick = loginViewModel::onLoginClick,
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