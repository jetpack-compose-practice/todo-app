package com.piyushprajpti.todo_app.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

val USER_KEY = stringPreferencesKey("id")

class UserPref(private val dataStore: DataStore<Preferences>) {
    suspend fun setId(id: String) {
        dataStore.edit {
            it[USER_KEY] = id
        }
    }

    fun getId(): Flow<String> {
        return dataStore.data.catch { emit(emptyPreferences()) }.map {
            it[USER_KEY] ?: ""
        }
    }
}