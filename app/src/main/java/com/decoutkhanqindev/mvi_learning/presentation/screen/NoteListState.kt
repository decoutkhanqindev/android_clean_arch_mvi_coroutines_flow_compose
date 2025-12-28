package com.decoutkhanqindev.mvi_learning.presentation.screen

import com.decoutkhanqindev.mvi_learning.domain.model.Note

sealed interface NoteListState {
  data object Initial : NoteListState
  data object Loading : NoteListState
  data class Success(val notes: List<Note> = emptyList()) : NoteListState
  data object Error : NoteListState
}