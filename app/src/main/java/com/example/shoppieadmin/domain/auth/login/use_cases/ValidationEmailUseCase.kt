package com.example.shoppieadmin.domain.auth.login.use_cases

import android.util.Patterns
import com.example.shoppieadmin.presentation.auth.login.validation.LoginEmailValidationType

class ValidationEmailUseCase {
    operator fun invoke(email: String): LoginEmailValidationType {

        if (email.isEmpty()) {
            return LoginEmailValidationType.EMPTY_EMAIL
        }
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches().not()) {
            return LoginEmailValidationType.INVALID_EMAIL
        }
        return LoginEmailValidationType.VALID_EMAIL
    }
}