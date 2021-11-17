package com.incetro.phrasescalculator.phraseslist.view

import com.incetro.phrasescalculator.model.Phrase

interface PhrasesCalculatorView {
    fun insertItemToPosition(position: Int, phrase: Phrase)
    fun scrollRecyclerToPosition(position: Int)
    fun requestFocusOnPositionAndShowSoftKeyboard(position: Int)
    fun setTextInItem(text: String, itemPosition: Int)
    fun removeItemAtPosition(itemPosition: Int)
    fun setItemsCursorToPosition(itemPosition: Int, cursorPosition: Int)
    fun setItemsCursorToEnd(itemPosition: Int)
    fun appendTextInItemAndSaveCursorPosition(text: String, itemPosition: Int)
    fun updateRowNumberItemsLowerThan(itemPosition: Int, delta: Int)
}