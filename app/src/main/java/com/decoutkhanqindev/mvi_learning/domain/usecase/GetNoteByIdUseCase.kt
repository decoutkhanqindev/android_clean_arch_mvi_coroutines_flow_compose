package com.decoutkhanqindev.mvi_learning.domain.usecase

import com.decoutkhanqindev.mvi_learning.domain.repository.NoteRepository

class GetNoteByIdUseCase(private val repository: NoteRepository) {
  suspend operator fun invoke(id: Long)= repository.getById(id)
}