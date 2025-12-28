package com.decoutkhanqindev.mvi_learning.presentation.screen

sealed interface NoteEvent {
  data object NavigateBack : NoteEvent
  data object NavigateToNoteDetailScreen : NoteEvent
  data object NavigateToAddNoteScreen : NoteEvent
  data object NavigateToUpdateNoteScreen : NoteEvent
  data object ShowDeleteNoteConfirmationDialog : NoteEvent
  data object ShowUpdateNoteConfirmationDialog : NoteEvent
  data object ShowPasswordKeyInputBottomSheet : NoteEvent
  data class ShowSnackBar(val message: String) : NoteEvent
}