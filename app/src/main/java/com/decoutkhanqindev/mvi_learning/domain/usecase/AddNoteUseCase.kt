package com.decoutkhanqindev.mvi_learning.domain.usecase

import com.decoutkhanqindev.mvi_learning.domain.model.Note
import com.decoutkhanqindev.mvi_learning.domain.repository.NoteRepository

class AddNoteUseCase(private val repository: NoteRepository) {
  suspend operator fun  invoke(note: Note)= repository.add(note)
}

