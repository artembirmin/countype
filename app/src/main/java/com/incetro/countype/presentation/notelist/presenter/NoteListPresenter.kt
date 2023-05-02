/*
 * My Application
 *
 * Created by artembirmin on 27/4/2022.
 */

package com.incetro.countype.presentation.notelist.presenter

import com.incetro.countype.common.navigation.AppRouter
import com.incetro.countype.common.navigation.Screens
import com.incetro.countype.common.presentation.base.basefragment.BasePresenter
import com.incetro.countype.data.repository.NoteRepository
import com.incetro.countype.entity.Note
import com.incetro.countype.presentation.note.view.NoteFragmentInitParams
import com.incetro.countype.presentation.notelist.adapter.NoteViewItem
import com.incetro.countype.presentation.notelist.view.NoteListView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class NoteListPresenter @Inject constructor(
    private val repository: NoteRepository,
    private val router: AppRouter
) : BasePresenter<NoteListView>() {

    override fun onFirstViewAttach() {
        repository.observeNotes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                viewState.showNotes(it.sortedBy { note -> note.lastUpdateTime.time }
                    .asReversed()
                    .map { note -> note.toNoteViewItem() })
            }
            .addDisposable()
    }

    fun onAddNoteClick() {
        viewState.showAddNoteDialog(onAddClick = { noteName: String ->
            repository.addNewNote(
                noteName
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { note ->
                    router.navigateTo(
                        Screens.NoteScreen(
                            NoteFragmentInitParams(note.id)
                        )
                    )
                }
        })
    }

    private fun Note.toNoteViewItem() = NoteViewItem(this) { note: Note ->
        router.navigateTo(
            Screens.NoteScreen(
                NoteFragmentInitParams(note.id)
            )
        )
    }

    override fun onBackPressed() {
        router.exit()
    }

    fun removeItemAt(note: Note) {
        repository.removeItem(note)
    }
}