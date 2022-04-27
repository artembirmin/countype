package com.incetro.countype.data.repository

import com.incetro.countype.data.database.note.NoteDao
import com.incetro.countype.data.database.note.toNote
import com.incetro.countype.entity.Note
import com.incetro.countype.entity.Record
import com.incetro.countype.entity.toNoteDto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository {

    override fun saveRecords(noteId: String, records: List<Record>): Completable {
        return noteDao.updateRecordsAndTime(noteId, records, Date())
    }

    override fun getNote(noteId: String): Single<Note> {
        return noteDao.getNoteDtoById(noteId).map { it.toNote() }
    }

    override fun addNote(note: Note): Completable {
        return noteDao.insert(note.toNoteDto())
    }

    override fun observeNotes(): Flowable<List<Note>> {
        return noteDao.observeNotes().map { it.map { noteDto -> noteDto.toNote() } }
    }

    override fun addNewNote(noteName: String): Single<Note> {
        val newNote =
            Note(
                id = UUID.randomUUID().toString(),
                name = noteName,
                lastUpdateTime = Date(),
                records = listOf(
                    Record(
                        id = UUID.randomUUID().toString(),
                        position = 1,
                        phrase = "",
                        answer = ""
                    )
                )
            )
        return noteDao.insert(newNote.toNoteDto()).andThen(Single.just(newNote))
    }
}