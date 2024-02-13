package com.piyushprajpti.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {

    @Insert
    suspend fun insertAllNotes(notes: List<Note>)

    @Query("SELECT * FROM Note")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM Note WHERE noteid = :noteid")
    fun getNoteDetail(noteid: String): Flow<Note>

    @Upsert
    suspend fun addOrUpdateNote(note: Note)

    @Query("DELETE FROM Note WHERE noteid = :noteid")
    suspend fun deleteNote(noteid: String)
}