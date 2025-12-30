package com.decoutkhanqindev.mvi_learning.di

import com.decoutkhanqindev.mvi_learning.presentation.screen.NoteViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModelModule = module {
  singleOf(::NoteViewModel)
}