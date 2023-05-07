/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 22/2/2022.
 */

package com.incetro.countype.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.incetro.countype.data.database.city.CityDao
import com.incetro.countype.data.database.city.CityDto
import com.incetro.countype.data.database.datestamp.DatestampDao
import com.incetro.countype.data.database.datestamp.DatestampDto
import com.incetro.countype.data.database.functiondescription.FunctionDescriptionDao
import com.incetro.countype.data.database.functiondescription.FunctionDescriptionDto
import com.incetro.countype.data.database.measure.MeasureDao
import com.incetro.countype.data.database.measure.MeasureDto
import com.incetro.countype.data.database.note.NoteDao
import com.incetro.countype.data.database.note.NoteDto
import com.incetro.countype.data.database.timestamp.TimestampDao
import com.incetro.countype.data.database.timestamp.TimestampDto

@Database(
    entities = [
        NoteDto::class,
        FunctionDescriptionDto::class,
        MeasureDto::class,
        CityDto::class,
        DatestampDto::class,
        TimestampDto::class
    ],
    version = AppDatabase.VERSION
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "countype_db"
        const val VERSION = 3
    }

    abstract fun noteDao(): NoteDao
    abstract fun functionDescriptionDao(): FunctionDescriptionDao
    abstract fun measureDao(): MeasureDao
    abstract fun cityDao(): CityDao
    abstract fun timestampDao(): TimestampDao
    abstract fun datestampDao(): DatestampDao
}