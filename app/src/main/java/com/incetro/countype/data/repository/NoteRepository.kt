/*
 * My Application
 *
 * Created by artembirmin on 26/4/2022.
 */

package com.incetro.countype.data.repository

import com.incetro.countype.entity.Note
import com.incetro.countype.entity.Record
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface NoteRepository {
    fun saveRecords(noteId: String, records: List<Record>): Completable
    fun getNote(noteId: String): Single<Note>
    fun addNote(note: Note): Completable
    fun observeNotes(): Flowable<List<Note>>
    fun addNewNote(noteName: String): Single<Note>
    fun removeItem(note: Note)
}