package com.example.shoppieadmin.domain.auth.main.use_cases

import com.example.shoppieadmin.domain.auth.main.datamanager.LocalUserManager

class SaveTokenUseCase(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(token: String) {
        localUserManager.saveAppToken(token)
    }
}