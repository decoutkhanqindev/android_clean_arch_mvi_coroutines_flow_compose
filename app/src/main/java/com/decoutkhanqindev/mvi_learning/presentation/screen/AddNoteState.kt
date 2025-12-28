package com.decoutkhanqindev.mvi_learning.presentation.screen

import com.decoutkhanqindev.mvi_learning.domain.model.Note

sealed interface AddNoteState {
  data object Initial : AddNoteState
  data class FillingDetail(val note: Note) : AddNoteState
  data object Loading : AddNoteState
  data object Success : AddNoteState
  data object Error : AddNoteState
}