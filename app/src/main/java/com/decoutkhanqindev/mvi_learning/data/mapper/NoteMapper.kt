package com.decoutkhanqindev.mvi_learning.data.mapper

import com.decoutkhanqindev.mvi_learning.data.db.entity.NoteEntity
import com.decoutkhanqindev.mvi_learning.domain.model.Note

fun NoteEntity.toDomain() = Note(
  id = this.id,
  title = this.title,
  description = this.description,
  isEncrypt = this.isEncrypt,
  passKey = this.passKey,
  createdAt = this.createdAt,
  modifiedAt = this.modifiedAt,
)

fun Note.toEntity() = NoteEntity(
  id = this.id,
  title = this.title,
  description = this.description,
  isEncrypt = this.isEncrypt,
  passKey = this.passKey,
  createdAt = this.createdAt,
  modifiedAt = this.modifiedAt,
)