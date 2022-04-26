/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 22/2/2022.
 */

package com.incetro.countype.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.incetro.countype.data.database.note.NoteDao
import com.incetro.countype.data.database.note.NoteDto

@Database(
    entities = [
        NoteDto::class
    ],
    version = AppDatabase.VERSION
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "countype_db"
        const val VERSION = 1
    }

    abstract fun noteDao(): NoteDao
}