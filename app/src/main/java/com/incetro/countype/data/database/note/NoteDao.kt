package com.incetro.countype.data.database.note

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.incetro.countype.data.database.BaseDao
import com.incetro.countype.entity.Record
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import java.util.*

@Dao
interface NoteDao : BaseDao<NoteDto> {

    @Query("SELECT * FROM ${NoteDto.TABLE_NAME} WHERE id = :id")
    fun getNoteDtoById(id: String): Single<NoteDto>

    @Query("UPDATE ${NoteDto.TABLE_NAME} SET records = :records, lastUpdateTime = :date WHERE id = :noteId")
    fun updateRecordsAndTime(noteId: String, records: List<Record>, date: Date): Completable

    @Transaction
    @Query("SELECT * FROM ${NoteDto.TABLE_NAME}")
    fun observeNotes(): Flowable<List<NoteDto>>
}