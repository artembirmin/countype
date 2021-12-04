package com.incetro.countype.presentation.note.presenter

import com.incetro.countype.presentation.note.view.RecordsListView

interface RecordsListPresenter {

    fun attachView(recordsListView: RecordsListView)

    fun onClickEnter(
        itemPosition: Int,
        selectionStart: Int,
        selectionEnd: Int,
        text: String
    )

    fun onClickDelete(
        itemPosition: Int,
        selectionStart: Int,
        text: String,
        itemsCount: Int
    )

    fun onPhraseTyping(phrase: String, itemPosition: Int)

}