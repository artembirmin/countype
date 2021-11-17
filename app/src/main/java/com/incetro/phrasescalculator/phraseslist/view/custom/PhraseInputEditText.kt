package com.incetro.phrasescalculator.phraseslist.view.custom

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.view.inputmethod.InputConnectionWrapper
import androidx.appcompat.widget.AppCompatEditText


class PhraseInputEditText : AppCompatEditText {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onCreateInputConnection(outAttrs: EditorInfo?): InputConnection {
        val connection = super.onCreateInputConnection(outAttrs)
        val imeActions = outAttrs!!.imeOptions and EditorInfo.IME_MASK_ACTION
        if (imeActions and EditorInfo.IME_ACTION_NEXT != 0) {
            // clear the existing action
            outAttrs.imeOptions = outAttrs.imeOptions xor imeActions
            // set the DONE action
            outAttrs.imeOptions = outAttrs.imeOptions or EditorInfo.IME_ACTION_NEXT
        }
        if (outAttrs.imeOptions and EditorInfo.IME_FLAG_NO_ENTER_ACTION !== 0) {
            outAttrs.imeOptions =
                outAttrs.imeOptions and EditorInfo.IME_FLAG_NO_ENTER_ACTION.inv()
        }
        return MyInputConnection(connection, true)
    }


    inner class MyInputConnection(target: InputConnection?, mutable: Boolean) :
        InputConnectionWrapper(target, mutable) {
        override fun sendKeyEvent(event: KeyEvent): Boolean {
            Log.d("PhraseInputEditText", "sendKeyEvent KeyEvent $event")

            keyListener.onKeyDown(
                this@PhraseInputEditText,
                this@PhraseInputEditText.text,
                event.keyCode, event
            )

            return super.sendKeyEvent(event)
        }
    }

}