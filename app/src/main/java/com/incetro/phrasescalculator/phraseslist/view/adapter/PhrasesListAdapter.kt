package com.incetro.phrasescalculator.phraseslist.view.adapter

import android.graphics.Paint
import android.graphics.Rect
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.incetro.phrasescalculator.R
import com.incetro.phrasescalculator.model.Phrase
import com.incetro.phrasescalculator.phraseslist.view.custom.PhraseInputEditText
import com.incetro.phrasescalculator.phraseslist.view.getWightForTextView

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

typealias OnMaxRowNumberWidthChange = (newValue: Int) -> Unit

class PhrasesListAdapter(
    private val phrases: MutableList<Phrase>,
    private val onEnterKeyClick: OnEnterKeyClick,
    private val onDeleteKeyClick: OnDeleteKeyClick,
    private val onMaxRowNumberWidthChange: OnMaxRowNumberWidthChange,
) : RecyclerView.Adapter<PhrasesListAdapter.PhraseViewHolder>() {

    private val TAG = "PhrasesListAdapter"
    private var maxRowNumberWidth: Int = 0
        set(value) {
            field = value + 6
            onMaxRowNumberWidthChange(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhraseViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_edittext_item, parent, false)
        return PhraseViewHolder(v)
    }

    override fun onBindViewHolder(holderPhrase: PhraseViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: position = $position")
        holderPhrase.bind(phrases[position], position)
    }

    override fun getItemCount(): Int {
        return phrases.size
    }

    fun insertItemToPosition(position: Int, phrase: Phrase) {
        Log.d(TAG, "insertItemToPosition: position = $position")
        phrases.add(position, phrase)
        notifyItemInserted(position)
    }

    fun removeItemAt(position: Int) {
        phrases.removeAt(position)
        notifyItemRemoved(position)
    }

    fun updatePhraseExpression(itemPosition: Int, text: String) {
        phrases[itemPosition].expression = text
        notifyItemChanged(itemPosition)
    }

    fun appendTextToPhraseExpression(text: String, itemPosition: Int) {
        phrases[itemPosition].expression += text
        notifyItemChanged(itemPosition)
    }

    fun updateRowNumberItemsLowerThan(itemPosition: Int, delta: Int) {
        (itemPosition + 1..phrases.lastIndex).forEach { position ->
            phrases[position].position += delta
        }
        notifyItemRangeChanged(itemPosition + 1, phrases.lastIndex - (itemPosition))
    }

    inner class PhraseViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val myCustomEditTextListener: MyCustomEditTextListener = MyCustomEditTextListener()

        val rowNumberTextView: TextView = itemView.findViewById(R.id.row_number_textview)

        var phraseInputEditText: PhraseInputEditText =
            itemView.findViewById(R.id.phrase_input_edittext)

        val answerTextView: TextView = itemView.findViewById(R.id.answer_textview)

        var selectionStartPosition: Int = 0

        private val currentPosition
            get() = absoluteAdapterPosition

        private var TAG = "PhraseViewHolder"

        init {
            phraseInputEditText.addTextChangedListener(myCustomEditTextListener)
            phraseInputEditText.setOnEditorActionListener { v, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_NEXT -> {
                        onEnterKeyClick(
                            currentPosition,
                            v.selectionStart,
                            v.selectionEnd,
                            v.text.toString()
                        )
                        checkMaxRowNumberWidth()
                        true
                    }
                    else -> false
                }
            }
            phraseInputEditText.setOnKeyListener { view, keyCode, event ->
                Log.d(TAG, "setOnKeyListener: event = $event")
                if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                    if (view is TextView) {

                        onDeleteKeyClick(
                            currentPosition,
                            view.selectionStart,
                            view.text.toString(),
                            itemCount
                        )
                        checkMaxRowNumberWidth()
                    }
                }
                true
            }
        }

        private fun checkMaxRowNumberWidth(){
            if(phrases.size == 1) {
                return
            }
            val lastPhrasePosition = phrases.last().position.toString()
            val lastPhrasePositionWight = lastPhrasePosition.getWightForTextView(rowNumberTextView)
            if(lastPhrasePositionWight > maxRowNumberWidth){
                maxRowNumberWidth = lastPhrasePositionWight
            }
            if(lastPhrasePositionWight < maxRowNumberWidth - 12){
                maxRowNumberWidth = lastPhrasePositionWight
            }
        }

        fun bind(phrase: Phrase, position: Int) {
            Log.d(
                TAG,
                "position = $position,\n" +
                        "rowNumberTextView.layoutParams.width = ${rowNumberTextView.layoutParams.width}"
            )
            phraseInputEditText.setText(phrase.expression)
            phraseInputEditText.setSelection(selectionStartPosition)

            checkMaxRowNumberWidth()

            val lp = rowNumberTextView.layoutParams
            lp.width = maxRowNumberWidth + 10
            rowNumberTextView.layoutParams = lp

            val phrasePosition = (phrase.position).toString()
            rowNumberTextView.setText(phrasePosition)
        }

        inner class MyCustomEditTextListener : TextWatcher {

            override fun beforeTextChanged(
                charSequence: CharSequence, start: Int, before: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence,
                start: Int,
                before: Int,
                after: Int
            ) {
                Log.d(
                    "TextWatcher",
                    "onTextChanged: start = $start, before = $before, after = $after"
                )
                phrases[currentPosition].expression = charSequence.toString()
            }

            override fun afterTextChanged(editable: Editable) {
            }
        }
    }
}
