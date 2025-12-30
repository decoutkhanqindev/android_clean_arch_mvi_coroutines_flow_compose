package com.decoutkhanqindev.mvi_learning.presentation.screen

import com.decoutkhanqindev.mvi_learning.domain.model.Note

sealed interface NoteIntent {
  data class AddNote(val note: Note) : NoteIntent
  data object GetAllNotes : NoteIntent
  data class GetNoteById(val id: Long) : NoteIntent
  data class SearchNotes(val query: String) : NoteIntent
  data class UpdateNote(val note: Note) : NoteIntent
  data class DeleteNote(val note: Note) : NoteIntent
  data class ChangePassKey(val key: String) : NoteIntent
  data object SavePassKey : NoteIntent
  data object SubmitPassKey : NoteIntent
}