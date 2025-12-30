package com.decoutkhanqindev.mvi_learning.di

import com.decoutkhanqindev.mvi_learning.data.storage.PassKeyStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val storageModule = module {
  single { PassKeyStorage(androidContext()) }
}