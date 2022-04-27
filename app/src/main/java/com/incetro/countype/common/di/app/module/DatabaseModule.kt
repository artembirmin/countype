/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 27/2/2022.
 */

package com.incetro.countype.common.di.app.module

import android.content.Context
import androidx.room.Room
import com.incetro.countype.data.database.AppDatabase
import com.incetro.countype.data.database.note.NoteDao
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(context: Context): AppDatabase {
        return Room
            .databaseBuilder(
                context,
                AppDatabase::class.java,
                AppDatabase.DB_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Reusable
    fun provideNoteDao(database: AppDatabase): NoteDao = database.noteDao()
}