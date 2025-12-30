package com.decoutkhanqindev.mvi_learning.data.repository

import com.decoutkhanqindev.mvi_learning.data.storage.PassKeyStorage
import com.decoutkhanqindev.mvi_learning.domain.repository.PassKeyRepository
import com.decoutkhanqindev.mvi_learning.util.runSuspendCatching
import com.decoutkhanqindev.mvi_learning.util.toResultFlow
import kotlinx.coroutines.Dispatchers

class PassKeyRepositoryImpl(private val storage: PassKeyStorage) : PassKeyRepository {
  override suspend fun saveKey(key: String) = runSuspendCatching(Dispatchers.IO) {
    storage.saveKey(key)
  }

  override fun getKey() = storage.getKey().toResultFlow()
}