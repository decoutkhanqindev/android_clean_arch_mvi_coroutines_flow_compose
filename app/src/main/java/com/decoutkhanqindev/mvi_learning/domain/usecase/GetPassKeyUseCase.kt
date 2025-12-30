package com.decoutkhanqindev.mvi_learning.domain.usecase

import com.decoutkhanqindev.mvi_learning.domain.repository.PassKeyRepository

class GetPassKeyUseCase(private val repository: PassKeyRepository) {
   operator fun invoke() = repository.getKey()
}

