package com.decoutkhanqindev.mvi_learning.presentation.screen

sealed interface NotePassKeyState {
  data object Initial : NotePassKeyState
  data class FillingPassKey(val passKey: String) : NotePassKeyState
  data object Correct : NotePassKeyState
  data object Incorrect : NotePassKeyState
}