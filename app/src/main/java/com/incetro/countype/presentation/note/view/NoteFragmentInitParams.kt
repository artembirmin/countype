package com.incetro.countype.presentation.note.view

import com.incetro.countype.common.navigation.InitParams
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteFragmentInitParams(val noteId: String) : InitParams
