package com.example.shoppieadmin.data.datamanager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.shoppieadmin.domain.main.datamanager.LocalUserManager
import com.example.shoppieadmin.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(
    private val context: Context
): LocalUserManager {
    override suspend fun saveAppToken(token: String) {
        context.datastore.edit { prefs ->
            prefs[PreferencesKeys.DATASTORE_TOKEN] = token
        }
    }


    override fun readAppToken(): Flow<String?> {
        return context.datastore.data.map { prefs ->
            prefs[PreferencesKeys.DATASTORE_TOKEN]
        }
    }
}

private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = Constants.DATASTORE_NAME)

private object PreferencesKeys {
    val DATASTORE_TOKEN = stringPreferencesKey(name = Constants.DATASTORE_TOKEN)
}
