package com.incetro.phrasescalculator.phraseslist.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.incetro.phrasescalculator.R
import com.incetro.phrasescalculator.model.Phrase
import com.incetro.phrasescalculator.phraseslist.presenter.PhrasesCalculatorPresenter
import com.incetro.phrasescalculator.phraseslist.presenter.PhrasesCalculatorPresenterImpl
import com.incetro.phrasescalculator.phraseslist.view.adapter.PhrasesListAdapter
import java.util.*


class PhrasesCalculatorFragment : Fragment(), PhrasesCalculatorView {

    private val presenter: PhrasesCalculatorPresenter = PhrasesCalculatorPresenterImpl(this)
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var phrasesListAdapter: PhrasesListAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recycler_view, container, false)
        recyclerView = view.findViewById(R.id.recyclerview)
        initRecyclerView()
        return view
    }

    private fun initRecyclerView() {
        layoutManager = LinearLayoutManager(context)
        phrasesListAdapter = PhrasesListAdapter(
            (0..9).map { Phrase(UUID.randomUUID().toString(),it+1, "строка №$it") }.toMutableList(),
            ::onEnterKeyClick,
            ::onDeleteKeyClick
        )
        with(recyclerView) {
            adapter = phrasesListAdapter
            layoutManager = LinearLayoutManager(context)
        }
        recyclerView.itemAnimator = null
    }

    private fun onEnterKeyClick(
        itemPosition: Int,
        selectionStart: Int,
        selectionEnd: Int,
        text: String
    ) {
        presenter.onClickEnter(
            itemPosition, selectionStart, selectionEnd, text
        )
    }

    private fun onDeleteKeyClick(
        itemPosition: Int,
        selectionStart: Int,
        text: String,
        itemCount: Int
    ) {
        presenter.onClickDelete(
            itemPosition, selectionStart, text, itemCount
        )
    }

    override fun setItemsCursorToPosition(itemPosition: Int, cursorPosition: Int) {
        val editText: EditText = getPhraseInputFieldByViewHolderPosition(itemPosition)
        editText.setSelection(cursorPosition)
    }

    override fun setItemsCursorToEnd(itemPosition: Int) {
        val editText: EditText = getPhraseInputFieldByViewHolderPosition(itemPosition)
        editText.setSelection(editText.text.length)
    }

    override fun insertItemToPosition(position: Int, phrase: Phrase) {
        phrasesListAdapter.insertItemToPosition(position, phrase)
    }

    override fun appendTextInItemAndSaveCursorPosition(text: String, itemPosition: Int) {
        val editText: EditText = getPhraseInputFieldByViewHolderPosition(itemPosition)
        val cursorPosition = editText.selectionStart
        editText.text.append(text)
        editText.setSelection(cursorPosition)
        phrasesListAdapter.appendTextToPhraseExpression(text, itemPosition)
    }

    override fun updateRowNumberItemsLowerThan(itemPosition: Int, delta: Int) {
        phrasesListAdapter.updateRowNumberItemsLowerThan(itemPosition, delta)
    }

    override fun setTextInItem(text: String, itemPosition: Int) {
        phrasesListAdapter.updatePhraseExpression(itemPosition, text)
    }

    override fun removeItemAtPosition(itemPosition: Int) {
        phrasesListAdapter.removeItemAt(itemPosition)
    }

    override fun scrollRecyclerToPosition(position: Int) {
        val lastCompletelyVisibleItemPosition =
            (recyclerView.layoutManager as LinearLayoutManager)
                .findLastCompletelyVisibleItemPosition()
        if (lastCompletelyVisibleItemPosition < position) {
            recyclerView.scrollToPosition(position)
        }
    }

    override fun requestFocusOnPositionAndShowSoftKeyboard(position: Int) {
        val editText = getPhraseInputFieldByViewHolderPosition(position)
        editText.requestFocus()
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun getPhraseInputFieldByViewHolderPosition(itemPosition: Int): EditText {
        val layoutPosition = 0
        val editTextPositionInCardView = 1
        val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
        val cardView = linearLayoutManager.findViewByPosition(itemPosition) as CardView
        val layout = cardView.get(layoutPosition) as ConstraintLayout
        return layout.get(editTextPositionInCardView) as EditText
    }

    companion object {
        fun newInstance(): PhrasesCalculatorFragment = PhrasesCalculatorFragment()
    }
}
