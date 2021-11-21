package com.incetro.phrasescalculator.phraseslist.view

import android.content.Context
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.incetro.phrasescalculator.R
import com.incetro.phrasescalculator.model.Record
import com.incetro.phrasescalculator.phraseslist.interactor.PhrasesCalculatorInteractorImpl
import com.incetro.phrasescalculator.phraseslist.presenter.PhrasesCalculatorPresenter
import com.incetro.phrasescalculator.phraseslist.presenter.PhrasesCalculatorPresenterImpl
import com.incetro.phrasescalculator.phraseslist.view.adapter.PhrasesListAdapter
import java.util.*


class PhrasesCalculatorFragment : Fragment(), PhrasesCalculatorView {

    private val TAG = "PhrasesCalculatorFragment"
    private val presenter: PhrasesCalculatorPresenter =
        PhrasesCalculatorPresenterImpl(this, PhrasesCalculatorInteractorImpl())
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
            (0..8).map { Record(UUID.randomUUID().toString(), it + 1, "строка №$it", "") }
                .toMutableList(),
            ::onEnterKeyClick,
            ::onDeleteKeyClick,
            ::onMaxRowNumberWidthChange,
            ::onPhraseTyping
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
        presenter.onClickEnter(itemPosition, selectionStart, selectionEnd, text)
    }

    private fun onDeleteKeyClick(
        itemPosition: Int,
        selectionStart: Int,
        text: String,
        itemCount: Int
    ) {
        presenter.onClickDelete(itemPosition, selectionStart, text, itemCount)
    }

    private fun onMaxRowNumberWidthChange(newValue: Int) {
        Log.d(
            TAG,
            "onMaxRowNumberWidthChange: recyclerView.size = ${recyclerView.layoutManager?.itemCount}"
        )
        getAllRowNumberTextView(0..recyclerView.layoutManager!!.itemCount).forEach { textView ->
            val lp = textView.layoutParams
            lp.width = newValue + 10
            textView.layoutParams = lp
        }
        android.os.Handler(Looper.getMainLooper())
            .postDelayed(
                {
                    recyclerView.adapter?.notifyItemRangeChanged(
                        0,
                        recyclerView.layoutManager!!.itemCount
                    )
                },
                20
            )
    }

    private fun onPhraseTyping(phrase: String, itemPosition: Int) {
        presenter.onPhraseTyping(phrase, itemPosition)
    }

    override fun setItemsCursorToPosition(itemPosition: Int, cursorPosition: Int) {
        val editText: EditText? = getPhraseInputFieldByViewHolderPosition(itemPosition)
        editText?.setSelection(cursorPosition)
    }

    override fun setItemsCursorToEnd(itemPosition: Int) {
        val editText: EditText? = getPhraseInputFieldByViewHolderPosition(itemPosition)
        val selectionStart = editText?.text?.length
        selectionStart?.let { editText.setSelection(it) }
    }

    override fun insertItemToPosition(position: Int, record: Record) {
        phrasesListAdapter.insertItemToPosition(position, record)
    }

    override fun appendTextInItemAndSaveCursorPosition(text: String, itemPosition: Int) {
        phrasesListAdapter.appendTextToPhraseExpression(text, itemPosition)
    }

    override fun updateRowNumberItemsLowerThan(itemPosition: Int, delta: Int) {
        phrasesListAdapter.updateRowNumberItemsLowerThan(itemPosition, delta)
    }

    override fun setAnswerToItem(answer: String, itemPosition: Int) {
        phrasesListAdapter.updateAnswerWithoutNotify(answer, itemPosition)
        getPhraseViewHolder(itemPosition)?.answerTextView?.text = answer
    }

    override fun setPhraseInItem(text: String, itemPosition: Int) {
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

    override fun requestFocusOnItemByPositionAndShowSoftKeyboard(itemPosition: Int) {
        val editText = getPhraseInputFieldByViewHolderPosition(itemPosition)
        editText?.requestFocus()
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun getPhraseCardView(itemPosition: Int): CardView? {
        val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
        return linearLayoutManager.findViewByPosition(itemPosition) as? CardView
    }

    private fun getPhraseViewHolder(itemPosition: Int): PhrasesListAdapter.PhraseViewHolder? {
        return getPhraseCardView(itemPosition)?.let { recyclerView.getChildViewHolder(it) }
                as? PhrasesListAdapter.PhraseViewHolder
    }

    private fun getPhraseInputFieldByViewHolderPosition(itemPosition: Int): EditText? {
        Log.d(TAG, "getPhraseInputFieldByViewHolderPosition: itemPosition = $itemPosition")
        val layoutPosition = 0
        val editTextPositionInCardView = 1

        val cardView = getPhraseCardView(itemPosition)
        val layout = cardView?.get(layoutPosition) as? ConstraintLayout
        return layout?.get(editTextPositionInCardView) as? EditText
    }

    private fun getAllRowNumberTextView(range: IntRange): List<TextView> {
        val layoutPosition = 0
        val rowNumberTextViewPositionInCardView = 0
        val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
        return range.map { position ->
            Log.d(TAG, "getAllRowNumberTextView: position = $position")
            val cardView = linearLayoutManager.findViewByPosition(position) as? CardView
            val layout = cardView?.get(layoutPosition) as? ConstraintLayout
            return@map layout?.get(rowNumberTextViewPositionInCardView) as? TextView
        }.filterNotNull()
            .toList()
    }

    companion object {
        fun newInstance(): PhrasesCalculatorFragment = PhrasesCalculatorFragment()
    }
}
