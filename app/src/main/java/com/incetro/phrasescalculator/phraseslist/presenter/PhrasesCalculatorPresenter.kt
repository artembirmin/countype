package com.incetro.phrasescalculator.phraseslist.presenter

interface PhrasesCalculatorPresenter {

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