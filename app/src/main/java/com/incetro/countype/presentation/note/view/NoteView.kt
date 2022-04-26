package com.incetro.countype.presentation.note.view

import com.incetro.countype.entity.Record

interface NoteView {
    fun insertItemToPosition(itemPosition: Int, record: Record)
    fun removeItemAtPosition(itemPosition: Int)
    fun appendPhraseInItem(phrase: String, itemPosition: Int)
    fun setPhraseInItem(phrase: String, itemPosition: Int)
    fun updateRowNumberItemsLowerThan(itemPosition: Int, delta: Int)
    fun setAnswerToItem(answer: String, itemPosition: Int)
    fun setItemsCursorToPosition(itemPosition: Int, cursorPosition: Int)
    fun setItemsCursorToEnd(itemPosition: Int)
    fun scrollRecyclerToPosition(itemPosition: Int)
    fun requestFocusOnItemByPositionAndShowSoftKeyboard(itemPosition: Int)
}