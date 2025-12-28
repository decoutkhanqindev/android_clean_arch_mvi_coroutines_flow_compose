package com.decoutkhanqindev.mvi_learning.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.decoutkhanqindev.mvi_learning.data.db.dao.NoteDao
import com.decoutkhanqindev.mvi_learning.data.db.entity.NoteEntity

@Database(
  entities = [NoteEntity::class],
  version = 1,
  exportSchema = false
)
abstract class NoteDatabase: RoomDatabase() {
  abstract fun dao(): NoteDao

  companion object {
    @Volatile
    private var INSTANCE: NoteDatabase? = null

    fun getInstance(context: Context) =
      INSTANCE ?: synchronized(lock = this) {
        INSTANCE ?: createDatabase(context).also { INSTANCE = it }
      }

    private fun createDatabase(context: Context) =
      Room.databaseBuilder(
        context = context,
        klass = NoteDatabase::class.java,
        name = "note.db"
      )
        .fallbackToDestructiveMigration(dropAllTables = false)
        .build()
  }
}