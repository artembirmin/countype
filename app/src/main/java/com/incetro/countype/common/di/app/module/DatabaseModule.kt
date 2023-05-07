/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 27/2/2022.
 */

package com.incetro.countype.common.di.app.module

import android.content.Context
import androidx.room.Room
import com.incetro.countype.data.database.AppDatabase
import com.incetro.countype.data.database.city.CityDao
import com.incetro.countype.data.database.datestamp.DatestampDao
import com.incetro.countype.data.database.functiondescription.FunctionDescriptionDao
import com.incetro.countype.data.database.measure.MeasureDao
import com.incetro.countype.data.database.note.NoteDao
import com.incetro.countype.data.database.timestamp.TimestampDao
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

    @Provides
    @Reusable
    fun provideFunctionDescriptionDao(database: AppDatabase): FunctionDescriptionDao =
            database.functionDescriptionDao()

    @Provides
    @Reusable
    fun provideMeasureDao(database: AppDatabase): MeasureDao = database.measureDao()

    @Provides
    @Reusable
    fun provideCityDao(database: AppDatabase): CityDao = database.cityDao()

    @Provides
    @Reusable
    fun provideDatestampDao(database: AppDatabase): DatestampDao = database.datestampDao()

    @Provides
    @Reusable
    fun provideTimestampDao(database: AppDatabase): TimestampDao = database.timestampDao()
}