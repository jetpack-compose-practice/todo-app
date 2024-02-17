package com.piyushprajpti.database

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

val USER_ID = stringPreferencesKey("id")
val USER_EMAIL = stringPreferencesKey("email")

class UserData(private val dataStore: DataStore<Preferences>) {
    suspend fun setId(id: String) {
        dataStore.edit {
            it[USER_ID] = id
        }
    }

    suspend fun setEmail(email: String) {
        dataStore.edit {
            it[USER_EMAIL] = email
        }
    }

    fun getId(): Flow<String> {
        return dataStore.data.catch { emit(emptyPreferences()) }.map {
            it[USER_ID] ?: ""
        }
    }

    fun getEmail(): Flow<String> {
        return dataStore.data.catch { emit(emptyPreferences()) }.map {
            it[USER_EMAIL] ?: ""
        }
    }
}