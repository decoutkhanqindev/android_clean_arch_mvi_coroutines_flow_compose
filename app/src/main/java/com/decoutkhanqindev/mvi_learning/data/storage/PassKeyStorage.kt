package com.decoutkhanqindev.mvi_learning.data.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val PASS_KEY = stringPreferencesKey("note_pass_key")
private val Context.dataStore by preferencesDataStore(name = "pass_key_store")

class PassKeyStorage(private val context: Context) {
  fun getKey() = context.dataStore.data
    .map { preferences ->
      preferences[PASS_KEY] ?: ""
    }

  // Lưu Passkey
  suspend fun saveKey(key: String) {
    context.dataStore.edit { preferences ->
      preferences[PASS_KEY] = key
    }
  }
}