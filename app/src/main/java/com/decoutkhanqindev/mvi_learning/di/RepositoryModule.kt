package com.decoutkhanqindev.mvi_learning.di

import com.decoutkhanqindev.mvi_learning.data.repository.NoteRepositoryImpl
import com.decoutkhanqindev.mvi_learning.data.repository.PassKeyRepositoryImpl
import com.decoutkhanqindev.mvi_learning.domain.repository.NoteRepository
import com.decoutkhanqindev.mvi_learning.domain.repository.PassKeyRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
  singleOf( ::NoteRepositoryImpl) { bind<NoteRepository>() }
  singleOf( ::PassKeyRepositoryImpl) { bind<PassKeyRepository>() }
}