package com.example.shoppieadmin.domain.auth.login.use_cases

import android.util.Patterns
import com.example.shoppieadmin.domain.auth.login.models.validation.LoginEmailValidationType
import com.example.shoppieadmin.domain.auth.login.models.validation.LoginPasswordValidationType

class ValidationPasswordUseCase {
    operator fun invoke(password: String): LoginPasswordValidationType {

        if (password.isEmpty()) {
            return LoginPasswordValidationType.EMPTY_PASSWORD
        }
        if (password.length < 8) {
            return LoginPasswordValidationType.INVALID_PASSWORD
        }
        return LoginPasswordValidationType.VALID_PASSWORD
    }
}