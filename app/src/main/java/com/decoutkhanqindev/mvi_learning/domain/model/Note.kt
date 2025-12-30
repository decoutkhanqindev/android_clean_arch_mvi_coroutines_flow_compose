package com.decoutkhanqindev.mvi_learning.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class Note(
  val id: Long,
  val title: String,
  val description: String,
  val isEncrypt: Boolean,
  val createdAt: Long,
  val modifiedAt: Long
)
