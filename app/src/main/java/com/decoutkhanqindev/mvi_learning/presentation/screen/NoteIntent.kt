package com.decoutkhanqindev.mvi_learning.presentation.screen

import com.decoutkhanqindev.mvi_learning.domain.model.Note

sealed interface NoteIntent {
  data class AddNote(val note: Note) : NoteIntent
  data object GetAllNotes : NoteIntent
  data class GetNoteById(val id: Long) : NoteIntent
  data class UpdateNote(val note: Note) : NoteIntent
  data class DeleteNote(val note: Note) : NoteIntent
  data class SetSearchNoteQuery(val query: String) : NoteIntent
  data class SetNotePasswordKey(val key: String) : NoteIntent
  data class SubmitNotePasswordKey(val note: Note) : NoteIntent
}