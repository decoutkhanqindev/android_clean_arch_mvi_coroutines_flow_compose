package com.decoutkhanqindev.mvi_learning.domain.usecase

import com.decoutkhanqindev.mvi_learning.domain.repository.NoteRepository

class SearchNotesUseCase(private val repository: NoteRepository) {
  operator fun invoke(query: String) = repository.search(query)
}