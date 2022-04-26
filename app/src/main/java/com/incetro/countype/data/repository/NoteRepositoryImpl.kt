package com.incetro.countype.data.repository

import com.incetro.countype.data.database.note.NoteDao
import com.incetro.countype.data.database.note.toNote
import com.incetro.countype.entity.Note
import com.incetro.countype.entity.Record
import com.incetro.countype.entity.toNoteDto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository {

    override fun saveRecords(noteId: String, records: List<Record>): Completable {
        return noteDao.updateRecords(noteId, records)
    }

    override fun getNote(noteId: String): Single<Note> {
        return noteDao.getNoteDtoById(noteId).map { it.toNote() }
    }

    override fun addNote(note: Note): Completable {
        return noteDao.insert(note.toNoteDto())
    }
}