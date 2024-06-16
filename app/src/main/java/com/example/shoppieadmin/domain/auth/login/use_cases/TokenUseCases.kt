package com.example.shoppieadmin.domain.auth.login.use_cases

data class TokenUseCases(
    val saveTokenUseCase: SaveTokenUseCase,
    val readTokenUseCase: ReadTokenUseCase
)
