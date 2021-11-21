package com.incetro.phrasescalculator.phraseslist.presenter

import android.os.Looper
import com.incetro.phrasescalculator.model.Record
import com.incetro.phrasescalculator.phraseslist.interactor.PhrasesCalculatorInteractor
import com.incetro.phrasescalculator.phraseslist.view.PhrasesCalculatorView
import java.util.*

class PhrasesCalculatorPresenterImpl(
    private val view: PhrasesCalculatorView,
    private val interactor: PhrasesCalculatorInteractor
) :
    PhrasesCalculatorPresenter {

    override fun onClickEnter(
        itemPosition: Int,
        selectionStart: Int,
        selectionEnd: Int,
        text: String
    ) {
        val newItemPosition = itemPosition + 1
        val leftPartOfText = text.substring(startIndex = 0, endIndex = selectionStart)
        val rightPartOfText = text.substring(startIndex = selectionEnd, endIndex = text.length)
        val answer = interactor.calculatePhrase(rightPartOfText)
        view.insertItemToPosition(
            newItemPosition,
            Record(UUID.randomUUID().toString(), newItemPosition + 1, rightPartOfText, answer)
        )
        view.setPhraseInItem(leftPartOfText, itemPosition)
        view.scrollRecyclerToPosition(newItemPosition)
        view.updateRowNumberItemsLowerThan(newItemPosition, 1)
        android.os.Handler(Looper.getMainLooper())
            .postDelayed(
                {
                    view.requestFocusOnItemByPositionAndShowSoftKeyboard(newItemPosition)
                },
                10
            )
    }

    override fun onClickDelete(
        itemPosition: Int,
        selectionStart: Int,
        text: String,
        itemsCount: Int
    ) {
        if (itemsCount == 1) {
            return
        }
        val upperItemPosition = itemPosition - 1
        if (upperItemPosition >= 0) {
            view.requestFocusOnItemByPositionAndShowSoftKeyboard(upperItemPosition)
            view.setItemsCursorToEnd(upperItemPosition)
            view.appendTextInItemAndSaveCursorPosition(text, upperItemPosition)
        } else {
            if (text.isNotEmpty()) {
                return
            }
            view.requestFocusOnItemByPositionAndShowSoftKeyboard(1)
            view.setItemsCursorToEnd(1)
        }
        view.removeItemAtPosition(itemPosition)
        view.updateRowNumberItemsLowerThan(upperItemPosition, -1)
    }

    override fun onPhraseTyping(phrase: String, itemPosition: Int) {
        val answer = interactor.calculatePhrase(phrase)
        view.setAnswerToItem(answer, itemPosition)
    }
}