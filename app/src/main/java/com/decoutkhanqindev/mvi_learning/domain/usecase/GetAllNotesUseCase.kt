package com.decoutkhanqindev.mvi_learning.domain.usecase

import com.decoutkhanqindev.mvi_learning.domain.repository.NoteRepository

class GetAllNotesUseCase(private val repository: NoteRepository) {
  operator fun invoke() = repository.getAll()
}