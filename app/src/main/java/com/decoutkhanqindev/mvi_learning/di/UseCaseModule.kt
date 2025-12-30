package com.decoutkhanqindev.mvi_learning.di

import com.decoutkhanqindev.mvi_learning.domain.usecase.AddNoteUseCase
import com.decoutkhanqindev.mvi_learning.domain.usecase.DeleteNoteUseCase
import com.decoutkhanqindev.mvi_learning.domain.usecase.GetAllNotesUseCase
import com.decoutkhanqindev.mvi_learning.domain.usecase.GetNoteByIdUseCase
import com.decoutkhanqindev.mvi_learning.domain.usecase.GetPassKeyUseCase
import com.decoutkhanqindev.mvi_learning.domain.usecase.SavePassKeyUseCase
import com.decoutkhanqindev.mvi_learning.domain.usecase.SearchNotesUseCase
import com.decoutkhanqindev.mvi_learning.domain.usecase.UpdateNoteUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
  singleOf(::AddNoteUseCase)
  singleOf(::GetAllNotesUseCase)
  singleOf(::GetNoteByIdUseCase)
  singleOf(::SearchNotesUseCase)
  singleOf(::UpdateNoteUseCase)
  singleOf(::DeleteNoteUseCase)
  singleOf(::SavePassKeyUseCase)
  singleOf(::GetPassKeyUseCase)
}