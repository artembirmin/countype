package com.incetro.countype.presentation.recordslist.view

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
import com.incetro.countype.R
import com.incetro.countype.common.AppActivity
import com.incetro.countype.di.recordslist.DaggerRecordsListComponent
import com.incetro.countype.model.Record
import com.incetro.countype.presentation.recordslist.presenter.RecordsListPresenter
import com.incetro.countype.presentation.recordslist.view.adapter.PhrasesListAdapter
import java.util.*
import javax.inject.Inject


class RecordsListFragment : Fragment(), RecordsListView {

    private val TAG = "RecordsListFragment"

    @Inject
    lateinit var presenter: RecordsListPresenter

    private lateinit var layoutManager: LinearLayoutManager

    private lateinit var phrasesListAdapter: PhrasesListAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inject()
        val view = inflater.inflate(R.layout.records_list_recyclerview, container, false)
        recyclerView = view.findViewById(R.id.records_list_recyclerview)
        initRecyclerView()
        presenter.attachView(this)
        return view
    }

    private fun inject() {
        DaggerRecordsListComponent.builder()
            .activityComponent(AppActivity.activityComponent) // KOLKHOZ
            .build().inject(this)
    }

    private fun initRecyclerView() {
        layoutManager = LinearLayoutManager(context)
        phrasesListAdapter = PhrasesListAdapter(
            (0..13).map { Record(UUID.randomUUID().toString(), it + 1, "$it% от 13", "") }
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
        fun newInstance(): RecordsListFragment = RecordsListFragment()
    }
}
