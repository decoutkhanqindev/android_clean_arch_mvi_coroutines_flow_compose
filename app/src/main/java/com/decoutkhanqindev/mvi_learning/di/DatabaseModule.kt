package com.decoutkhanqindev.mvi_learning.di

import com.decoutkhanqindev.mvi_learning.data.db.NoteDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
  single { NoteDatabase.getInstance(context = androidContext()) }
  single { get<NoteDatabase>().dao() }
}