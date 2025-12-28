package com.decoutkhanqindev.mvi_learning.presentation.screen

import com.decoutkhanqindev.mvi_learning.domain.model.Note

sealed interface NoteDetailState {
  data object Initial : NoteDetailState
  data object Loading : NoteDetailState
  data class Success(
    val note: Note,
    val updateState: NoteActionState = NoteActionState.Initial,
    val deleteState: NoteActionState = NoteActionState.Initial,
  ) : NoteDetailState
  data object Error : NoteDetailState
}

sealed interface NoteActionState {
  data object Initial : NoteActionState
  data object Loading : NoteActionState
  data object Success : NoteActionState
  data object Error : NoteActionState
}