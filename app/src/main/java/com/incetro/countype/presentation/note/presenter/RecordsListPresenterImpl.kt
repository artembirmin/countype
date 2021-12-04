package com.incetro.countype.presentation.note.presenter

import android.os.Looper
import com.incetro.countype.model.Record
import com.incetro.countype.presentation.note.interactor.RecordsListInteractor
import com.incetro.countype.presentation.note.view.RecordsListView
import java.util.*

class RecordsListPresenterImpl(
    private val interactor: RecordsListInteractor
) : RecordsListPresenter {

    private lateinit var view: RecordsListView

    override fun attachView(recordsListView: RecordsListView) {
        view = recordsListView
    }

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