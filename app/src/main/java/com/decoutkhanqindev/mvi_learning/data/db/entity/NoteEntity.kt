package com.decoutkhanqindev.mvi_learning.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0,
  val title: String,
  val description: String,
  @ColumnInfo(name = "is_encrypt")
  val isEncrypt: Boolean = false,
  val passKey: String? = null,
  @ColumnInfo(name = "created_at")
  val createdAt: Long = System.currentTimeMillis(),
  @ColumnInfo(name = "modified_at")
  val modifiedAt: Long
)