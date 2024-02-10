package com.piyushprajpti.todo_app.components

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.getDataStore by preferencesDataStore("datastore")


val URL = "https://hedgehog-wondrous-airedale.ngrok-free.app/"