package com.decoutkhanqindev.mvi_learning.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.decoutkhanqindev.mvi_learning.data.db.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(note: NoteEntity)

  @Query("SELECT * FROM notes")
  fun getAll(): Flow<List<NoteEntity>>

  @Query("SELECT * FROM notes WHERE id = :id LIMIT 1")
  suspend fun getById(id: Long): NoteEntity?

  @Query("SELECT * FROM notes WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
  fun search(query: String): Flow<List<NoteEntity>>

  @Update
  suspend fun update(note: NoteEntity)

  @Delete
  suspend fun delete(note: NoteEntity)
}