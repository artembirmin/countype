/*
 * My Application
 *
 * Created by artembirmin on 26/4/2022.
 */

package com.incetro.countype.entity

import androidx.room.PrimaryKey
import com.incetro.countype.data.database.note.NoteDto
import java.util.*

data class Note(
    @PrimaryKey
    val id: String,
    val name: String,
    val date: Date,
    val records: List<Record>
)

fun Note.toNoteDto(): NoteDto = NoteDto(
    id = id,
    name = name,
    date = date,
    records = records
)
