/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 5/3/2022.
 */

package com.incetro.countype.app

import com.incetro.countype.common.navigation.AppRouter
import com.incetro.countype.common.navigation.Screens
import com.incetro.countype.data.repository.NoteRepository

class AppLauncher(
    private val router: AppRouter,
    private val repository: NoteRepository
) {
    /**
     *  Initialized and launches application.
     */
    fun start() {
//        repository.addNote(
//            Note(
//                id = Constants.ROOT_NOTE_ID,
//                name = Constants.ROOT_NOTE_NAME,
//                lastUpdateTime = Date(),
//                records = listOf(
//                    Record(
//                        id = UUID.randomUUID().toString(),
//                        position = 1,
//                        phrase = "",
//                        answer = ""
//                    )
//                )
//            )
//        ).subscribeOn(Schedulers.io())
//            .subscribe()
        router.newRootScreen(
            Screens.NoteListScreen
        )
    }
}