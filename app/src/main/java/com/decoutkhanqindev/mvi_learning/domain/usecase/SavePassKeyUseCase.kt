package com.decoutkhanqindev.mvi_learning.domain.usecase

import com.decoutkhanqindev.mvi_learning.domain.repository.PassKeyRepository

class SavePassKeyUseCase(private val repository: PassKeyRepository) {
   suspend operator fun invoke(key: String) = repository.saveKey(key)
}