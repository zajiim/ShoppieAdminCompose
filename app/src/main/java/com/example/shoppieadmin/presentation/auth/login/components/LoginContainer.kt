package com.example.shoppieadmin.presentation.auth.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.shoppieadmin.R
import com.example.shoppieadmin.ui.theme.Orange

@Composable
fun LoginContainer(
    emailValue: () -> String,
    passwordValue: () -> String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    buttonEnabled: () -> Boolean,
    onLoginClick: () -> Unit,
    isPasswordVisible: () -> Boolean,
    onTrailingIconClick: () -> Unit,
    onErrorHint: () -> String?,
    isLoading: () -> Boolean,
    buttonBackgroundColor: Color,
    buttonTextColor: Color,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        val visiblePasswordIcon = ImageVector.vectorResource(id = R.drawable.visibility_on)
        val inVisiblePasswordIcon = ImageVector.vectorResource(id = R.drawable.visibility_off)

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            title = "Email",
            textValue = emailValue(),
            hint = "Enter your email",
            textColor = Color.Gray,
            cursorColor = Orange,
            onValueChange = onEmailChanged,
            onTrailingIconClicked = null,
            trailingIcon = Icons.Default.Email,
            errorString = onErrorHint()
        )

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            title = "Password",
            textValue = passwordValue(),
            hint = "Enter your password",
            textColor = Color.Gray,
            cursorColor = Orange,
            onValueChange = onPasswordChanged,
            onTrailingIconClicked = { onTrailingIconClick() },
            visualTransformation = if (isPasswordVisible()) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password,
            trailingIcon = if (isPasswordVisible()) visiblePasswordIcon else inVisiblePasswordIcon,
            errorString = onErrorHint() ?: ""
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CustomButton(
                text = "Login",
                backgroundColor = buttonBackgroundColor,
                contentColor = buttonTextColor,
                onButtonClicked = onLoginClick,
                isLoading = isLoading(),
                enabled = buttonEnabled(),
                modifier = Modifier.fillMaxWidth()
            )
        }

    }

}