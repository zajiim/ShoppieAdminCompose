package com.example.shoppieadmin.domain.auth.main.use_cases

data class TokenUseCases(
    val saveTokenUseCase: SaveTokenUseCase,
    val readTokenUseCase: ReadTokenUseCase
)
