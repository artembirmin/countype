/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 5/3/2022.
 */

package com.incetro.countype.app

import com.incetro.countype.common.Constants
import com.incetro.countype.common.navigation.AppRouter
import com.incetro.countype.common.navigation.Screens
import com.incetro.countype.data.repository.NoteRepository
import com.incetro.countype.entity.Note
import com.incetro.countype.entity.Record
import com.incetro.countype.presentation.note.view.NoteFragmentInitParams
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class AppLauncher(
    private val router: AppRouter,
    private val repository: NoteRepository
) {
    /**
     *  Initialized and launches application.
     */
    fun start() {
        repository.addNote(
            Note(
                id = Constants.ROOT_NOTE_ID,
                name = Constants.ROOT_NOTE_NAME,
                date = Date(),
                records = listOf(
                    Record(
                        id = UUID.randomUUID().toString(),
                        position = 1,
                        phrase = "",
                        answer = ""
                    )
                )
            )
        ).subscribeOn(Schedulers.io())
            .subscribe()
        router.newRootScreen(
            Screens.NoteScreen(NoteFragmentInitParams(Constants.ROOT_NOTE_ID))
        )
    }
}