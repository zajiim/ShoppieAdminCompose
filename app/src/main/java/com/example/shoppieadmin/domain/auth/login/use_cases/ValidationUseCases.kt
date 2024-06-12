package com.example.shoppieadmin.domain.auth.login.use_cases

data class ValidationUseCases(
    val validationEmailUseCase: ValidationEmailUseCase,
    val validationPasswordUseCase: ValidationPasswordUseCase
)
