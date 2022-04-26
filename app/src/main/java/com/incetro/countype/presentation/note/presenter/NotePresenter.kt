package com.incetro.countype.presentation.note.presenter

import com.incetro.countype.presentation.note.view.NoteView

interface NotePresenter {

    fun attachView(noteView: NoteView)

    fun onClickEnter(
        itemPosition: Int,
        selectionStart: Int,
        selectionEnd: Int,
        text: String
    )

    fun onClickDelete(
        itemPosition: Int,
        text: String,
        itemsCount: Int
    )

    fun onPhraseTyping(phrase: String, itemPosition: Int)

}