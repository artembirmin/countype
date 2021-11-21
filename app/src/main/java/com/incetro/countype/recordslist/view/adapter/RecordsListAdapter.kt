package com.incetro.countype.recordslist.view.adapter

import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.incetro.countype.R
import com.incetro.countype.model.Record
import com.incetro.countype.recordslist.view.custom.PhraseInputEditText
import com.incetro.countype.recordslist.view.getMaxWidhtOfCountOfSymbols
import com.incetro.countype.recordslist.view.getWidhtForTextView

typealias OnEnterKeyClick = (
    itemPosition: Int,
    selectionStart: Int,
    selectionEnd: Int,
    text: String
) -> Unit

typealias OnDeleteKeyClick = (
    itemPosition: Int,
    selectionStart: Int,
    text: String,
    itemCount: Int
) -> Unit

typealias OnPhraseTyping = (
    phraseExpression: String,
    itemPosition: Int,
) -> Unit

typealias OnMaxRowNumberWidthChange = (newValue: Int) -> Unit

class PhrasesListAdapter(
    private val records: MutableList<Record>,
    private val onEnterKeyClick: OnEnterKeyClick,
    private val onDeleteKeyClick: OnDeleteKeyClick,
    private val onMaxRowNumberWidthChange: OnMaxRowNumberWidthChange,
    private val onPhraseTyping: OnPhraseTyping,
) : RecyclerView.Adapter<PhrasesListAdapter.PhraseViewHolder>() {

    private val TAG = "PhrasesListAdapter"
    private var maxRowNumberWidth: Int = 0
        set(value) {
            field = value + 6
            onMaxRowNumberWidthChange(value)
        }

    // Overridden methods

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhraseViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.record_item, parent, false)
        return PhraseViewHolder(v)
    }

    override fun onBindViewHolder(holderPhrase: PhraseViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: position = $position")
        holderPhrase.bind(records[position], position)
    }

    override fun getItemCount(): Int {
        return records.size
    }

    // Методы изменения содержимиого списка

    // Добваление

    fun insertItemToPosition(position: Int, record: Record) {
        Log.d(TAG, "insertItemToPosition: position = $position")
        records.add(position, record)
        notifyItemInserted(position)
    }

    // Удаление

    fun removeItemAt(position: Int) {
        records.removeAt(position)
        notifyItemRemoved(position)
    }

    // Обновление, изменение

    fun updatePhraseExpression(itemPosition: Int, text: String) {
        records[itemPosition].phrase = text
        notifyItemChanged(itemPosition)
    }

    fun updateRowNumberItemsLowerThan(itemPosition: Int, delta: Int) {
        (itemPosition + 1..records.lastIndex).forEach { position ->
            records[position].position += delta
        }
        notifyItemRangeChanged(itemPosition + 1, records.lastIndex - (itemPosition))
    }

    fun updateAnswerWithoutNotify(answer: String, itemPosition: Int) {
        records[itemPosition].answer = answer
    }

    fun appendTextToPhraseExpression(text: String, itemPosition: Int) {
        records[itemPosition].phrase += text
        notifyItemChanged(itemPosition)
    }

    inner class PhraseViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private var TAG = "PhraseViewHolder"

        // Views

        private val positionTextView: TextView = itemView.findViewById(R.id.position_textview)

        private val phraseInputEditText: PhraseInputEditText =
            itemView.findViewById(R.id.phrase_input_edittext)

        val answerTextView: TextView = itemView.findViewById(R.id.answer_textview)

        // Other

        private val currentItemPosition
            get() = absoluteAdapterPosition

        init {
            phraseInputEditText.doOnTextChanged { text, _, _, _ ->
                text?.let {
                    savePhraseToRecords(text)
                    onPhraseTyping(text.toString(), currentItemPosition)
                }
            }
            phraseInputEditText.setOnEditorActionListener { v, actionId, _ ->
                onActionNext(v, actionId)
            }
            phraseInputEditText.setOnKeyListener { view, keyCode, event ->
                onKeyDelete(keyCode, event, view)
            }
        }

        // Методы листенеров

        private fun savePhraseToRecords(text: CharSequence) {
            records[currentItemPosition].phrase = text.toString()
        }

        private fun onActionNext(v: TextView, actionId: Int) = when (actionId) {
            EditorInfo.IME_ACTION_NEXT -> {
                onEnterKeyClick(
                    currentItemPosition,
                    v.selectionStart,
                    v.selectionEnd,
                    v.text.toString()
                )
                checkMaxRowNumberWidth()
                true
            }
            else -> false
        }

        private fun onKeyDelete(
            keyCode: Int,
            event: KeyEvent,
            view: View?
        ): Boolean {
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                if (view is TextView) {
                    onDeleteKeyClick(
                        currentItemPosition,
                        view.selectionStart,
                        view.text.toString(),
                        itemCount
                    )
                    checkMaxRowNumberWidth()
                }
            }
            return true
        }

        // onBind

        fun bind(record: Record, position: Int) {

            phraseInputEditText.setText(record.phrase)
            answerTextView.setText(record.answer)

            checkMaxRowNumberWidth()

            setPositionTextViewWidth(maxRowNumberWidth)

            val itemPosition = (record.position).toString()
            positionTextView.setText(itemPosition)
        }

        //  position_text_view width

        private fun checkMaxRowNumberWidth() {
            if (maxRowNumberWidth == 0) {
                initMaxRowNumberWidth()
            }
            if (records.size == 1) {
                return
            }
            val lastPhrasePosition = records.last().position.toString()
            val lastPhrasePositionWidht = lastPhrasePosition.getWidhtForTextView(positionTextView)

            if (lastPhrasePositionWidht > maxRowNumberWidth) {
                maxRowNumberWidth = lastPhrasePositionWidht
            }
            if (lastPhrasePositionWidht < (maxRowNumberWidth -
                        positionTextView.getMaxWidhtOfCountOfSymbols(1))
                && lastPhrasePosition.length >= 2
            ) {
                maxRowNumberWidth = lastPhrasePositionWidht
            }
        }

        private fun initMaxRowNumberWidth() {
            maxRowNumberWidth = positionTextView.getMaxWidhtOfCountOfSymbols(2)
        }

        private fun setPositionTextViewWidth(width: Int) {
            val lp = positionTextView.layoutParams
            lp.width = width
            positionTextView.layoutParams = lp
        }

    }
}
