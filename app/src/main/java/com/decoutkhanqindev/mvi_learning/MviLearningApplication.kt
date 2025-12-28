package com.decoutkhanqindev.mvi_learning

import android.app.Application
import com.decoutkhanqindev.mvi_learning.di.databaseModule
import com.decoutkhanqindev.mvi_learning.di.repositoryModule
import com.decoutkhanqindev.mvi_learning.di.useCaseModule
import com.decoutkhanqindev.mvi_learning.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MviLearningApplication: Application() {
  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidLogger()
      androidContext(this@MviLearningApplication)
      modules(
        modules = listOf(
          databaseModule,
          repositoryModule,
          useCaseModule,
          viewModelModule
        )
      )
    }
  }
}