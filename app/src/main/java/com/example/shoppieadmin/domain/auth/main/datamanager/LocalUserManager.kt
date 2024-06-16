package com.example.shoppieadmin.domain.auth.main.datamanager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {

    suspend fun saveAppToken(token: String)

    fun readAppToken(): Flow<String?>
}