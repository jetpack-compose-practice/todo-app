package com.piyushprajpti.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NotesDB : RoomDatabase() {
    abstract fun noteDao(): NoteDAO

    companion object {
        private var INSTANCE: NotesDB? = null
        fun getDatabase(context: Context): NotesDB {

            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        NotesDB::class.java,
                        "notesdb"
                    ).build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}