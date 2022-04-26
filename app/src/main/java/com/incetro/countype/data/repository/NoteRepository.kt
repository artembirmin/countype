package com.incetro.countype.data.repository

import com.incetro.countype.entity.Note
import com.incetro.countype.entity.Record
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface NoteRepository {
    fun saveRecords(noteId: String, records: List<Record>): Completable
    fun getNote(noteId: String): Single<Note>
    fun addNote(note: Note): Completable
}