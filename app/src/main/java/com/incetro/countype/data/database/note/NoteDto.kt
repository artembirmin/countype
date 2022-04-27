package com.incetro.countype.data.database.note

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.incetro.countype.entity.Note
import com.incetro.countype.entity.Record
import java.util.*

@Entity(
    tableName = NoteDto.TABLE_NAME
)
data class NoteDto(
    @PrimaryKey
    val id: String,
    val name: String,
    val lastUpdateTime: Date,
    val records: List<Record>
) {
    companion object {
        const val TABLE_NAME = "note"
    }
}

fun NoteDto.toNote(): Note = Note(
    id = id,
    name = name,
    lastUpdateTime = lastUpdateTime,
    records = records
)