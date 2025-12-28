package com.decoutkhanqindev.mvi_learning.domain.usecase

import com.decoutkhanqindev.mvi_learning.domain.model.Note
import com.decoutkhanqindev.mvi_learning.domain.repository.NoteRepository

class UpdateNoteUseCase(private val repository: NoteRepository) {
  suspend operator fun invoke(note: Note) = repository.update(note)
}