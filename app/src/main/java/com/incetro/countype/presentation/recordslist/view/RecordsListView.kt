package com.incetro.countype.presentation.recordslist.view

import com.incetro.countype.model.Record

interface RecordsListView {
    fun insertItemToPosition(position: Int, record: Record)
    fun scrollRecyclerToPosition(position: Int)
    fun requestFocusOnItemByPositionAndShowSoftKeyboard(position: Int)
    fun setPhraseInItem(text: String, itemPosition: Int)
    fun removeItemAtPosition(itemPosition: Int)
    fun setItemsCursorToPosition(itemPosition: Int, cursorPosition: Int)
    fun setItemsCursorToEnd(itemPosition: Int)
    fun appendTextInItemAndSaveCursorPosition(text: String, itemPosition: Int)
    fun updateRowNumberItemsLowerThan(itemPosition: Int, delta: Int)
    fun setAnswerToItem(answer: String, itemPosition: Int)
}