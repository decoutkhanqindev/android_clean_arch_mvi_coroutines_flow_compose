package com.decoutkhanqindev.mvi_learning.domain.repository

import com.decoutkhanqindev.mvi_learning.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
  suspend fun add(note: Note): Result<Unit>
  fun getAll(): Flow<Result<List<Note>>>
  suspend fun getById(id: Long): Result<Note>
  fun search(query: String): Flow<Result<List<Note>>>
  suspend fun update(note: Note): Result<Unit>
  suspend fun delete(note: Note): Result<Unit>
}