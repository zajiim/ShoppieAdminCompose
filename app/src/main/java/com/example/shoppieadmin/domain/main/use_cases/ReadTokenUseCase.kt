package com.example.shoppieadmin.domain.main.use_cases

import com.example.shoppieadmin.domain.main.datamanager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadTokenUseCase(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(): Flow<String?> {
        return localUserManager.readAppToken()
    }
}