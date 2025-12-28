package com.decoutkhanqindev.mvi_learning.data.repository

import com.decoutkhanqindev.mvi_learning.data.db.dao.NoteDao
import com.decoutkhanqindev.mvi_learning.data.mapper.toDomain
import com.decoutkhanqindev.mvi_learning.data.mapper.toEntity
import com.decoutkhanqindev.mvi_learning.domain.model.Note
import com.decoutkhanqindev.mvi_learning.domain.repository.NoteRepository
import com.decoutkhanqindev.mvi_learning.util.runSuspendCatching
import com.decoutkhanqindev.mvi_learning.util.toResultFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(private val dao: NoteDao) : NoteRepository {
  override suspend fun add(note: Note) = runSuspendCatching(Dispatchers.IO) {
    dao.insert(note.toEntity())
  }

  override fun getAll() =
    dao.getAll()
      .map { entities -> entities.map { it.toDomain() } }
      .toResultFlow()

  override suspend fun getById(id: Long) = runSuspendCatching(Dispatchers.IO) {
    dao.getById(id)?.toDomain() ?: throw NoSuchElementException("Note with id $id not found")
  }

  override fun search(query: String) =
    dao.search(query)
      .map { entities -> entities.map { it.toDomain() } }
      .toResultFlow()

  override suspend fun update(note: Note) = runSuspendCatching(Dispatchers.IO) {
    dao.update(note.toEntity())
  }

  override suspend fun delete(note: Note) = runSuspendCatching(Dispatchers.IO) {
    dao.delete(note.toEntity())
  }
}