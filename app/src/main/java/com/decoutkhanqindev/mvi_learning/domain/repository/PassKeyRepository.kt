package com.decoutkhanqindev.mvi_learning.domain.repository

import kotlinx.coroutines.flow.Flow

interface PassKeyRepository {
  suspend fun saveKey(key: String): Result<Unit>
  fun getKey(): Flow<Result<String>>
}