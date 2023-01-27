package com.example.notesapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesapp.models.Note


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (note: Note)

    @Delete
    suspend fun delete (note: Note)

    @Update
    suspend fun update (note: Note)

    @Query("SELECT * FROM notes_table order by id ASC")
    fun getAllNotes() : LiveData<List<Note>>

    @Query("DELETE FROM notes_table")
    suspend fun deleteAllNotes(note: Note)


}