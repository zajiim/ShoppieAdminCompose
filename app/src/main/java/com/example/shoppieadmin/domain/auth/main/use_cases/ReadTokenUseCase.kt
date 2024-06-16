package com.example.shoppieadmin.domain.auth.main.use_cases

import com.example.shoppieadmin.domain.auth.main.datamanager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadTokenUseCase(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(): Flow<String?> {
        return localUserManager.readAppToken()
    }
}