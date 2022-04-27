package com.incetro.countype.common.navigation

import com.incetro.countype.presentation.note.view.NoteFragment
import com.incetro.countype.presentation.note.view.NoteFragmentInitParams
import com.incetro.countype.presentation.notelist.view.NoteListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    data class NoteScreen(val initParams: NoteFragmentInitParams) : SupportAppScreen() {
        override fun getFragment() = NoteFragment.newInstance(initParams)
    }

    object NoteListScreen : SupportAppScreen() {
        override fun getFragment() = NoteListFragment.newInstance()
    }
}