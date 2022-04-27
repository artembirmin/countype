package com.incetro.countype.presentation.note.view

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
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.incetro.countype.R
import com.incetro.countype.common.navigation.AppRouter
import com.incetro.countype.common.navigation.getInitParams
import com.incetro.countype.common.navigation.saveInitParams
import com.incetro.countype.common.presentation.base.basefragment.BaseFragment
import com.incetro.countype.data.repository.NoteRepository
import com.incetro.countype.entity.Record
import com.incetro.countype.presentation.note.di.NoteComponent
import com.incetro.countype.presentation.note.presenter.NotePresenter
import com.incetro.countype.presentation.note.view.adapter.RecordsListAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class NoteFragment : BaseFragment<Nothing>(), NoteView {

    private val TAG = "NoteFragment"

    override val layoutRes: Int = R.layout.records_list_recyclerview

    @Inject
    lateinit var presenter: NotePresenter

    @Inject
    lateinit var repository: NoteRepository

    @Inject
    lateinit var router: AppRouter

    private val noteId: String by lazy { getInitParams<NoteFragmentInitParams>().noteId }

    private lateinit var recordsListAdapter: RecordsListAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inject()
        val view = inflater.inflate(R.layout.records_list_recyclerview, container, false)
        presenter.attachView(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.records_list_recyclerview)
        repository.getNote(noteId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                initRecyclerView(it.records)
                showNoteName(it.name)
            }, {})

    }

    private fun showNoteName(name: String) {
        requireView().findViewById<Toolbar>(R.id.toolbar)
            .apply {
                findViewById<TextView>(R.id.note_screen_toolbar_title)?.text = name
                setNavigationOnClickListener {
                    router.exit()
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        repository.saveRecords(
            getInitParams<NoteFragmentInitParams>().noteId,
            recordsListAdapter.items
        ).subscribeOn(Schedulers.io())
            .subscribe()
    }

    private fun initRecyclerView(records: List<Record>) {
        recordsListAdapter = RecordsListAdapter(
            records.toMutableList(),
            ::onEnterKeyClick,
            ::onDeleteKeyClick,
            ::onMaxRowNumberWidthChange,
            ::onPhraseTyping
        )
        with(recyclerView) {
            adapter = recordsListAdapter
            layoutManager = LinearLayoutManager(context)
        }
        recyclerView.itemAnimator = null
    }

    override fun inject() = NoteComponent.Manager.getComponent().inject(this)
    override fun release() = NoteComponent.Manager.releaseComponent()

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
        text: String,
        itemCount: Int
    ) {
        presenter.onClickDelete(itemPosition, text, itemCount)
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

    override fun insertItemToPosition(itemPosition: Int, record: Record) {
        recordsListAdapter.insertItemToPosition(itemPosition, record)
    }

    override fun appendPhraseInItem(phrase: String, itemPosition: Int) {
        recordsListAdapter.appendTextToPhraseExpression(phrase, itemPosition)
    }

    override fun updateRowNumberItemsLowerThan(itemPosition: Int, delta: Int) {
        recordsListAdapter.updateRowNumberItemsLowerThan(itemPosition, delta)
    }

    override fun setAnswerToItem(answer: String, itemPosition: Int) {
        recordsListAdapter.updateAnswerWithoutNotify(answer, itemPosition)
        getPhraseViewHolder(itemPosition)?.answerTextView?.text = answer
    }

    override fun setPhraseInItem(phrase: String, itemPosition: Int) {
        recordsListAdapter.updatePhraseExpression(itemPosition, phrase)
    }

    override fun removeItemAtPosition(itemPosition: Int) {
        recordsListAdapter.removeItemAt(itemPosition)
    }

    override fun scrollRecyclerToPosition(itemPosition: Int) {
        val lastCompletelyVisibleItemPosition =
            (recyclerView.layoutManager as LinearLayoutManager)
                .findLastCompletelyVisibleItemPosition()
        if (lastCompletelyVisibleItemPosition < itemPosition) {
            recyclerView.scrollToPosition(itemPosition)
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

    private fun getPhraseViewHolder(itemPosition: Int): RecordsListAdapter.RecordViewHolder? {
        return getPhraseCardView(itemPosition)?.let { recyclerView.getChildViewHolder(it) }
                as? RecordsListAdapter.RecordViewHolder
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
        fun newInstance(initParams: NoteFragmentInitParams): NoteFragment =
            NoteFragment().saveInitParams(initParams) as NoteFragment
    }
}
