package com.incetro.phrasescalculator.phraseslist.view

import android.graphics.Paint
import android.graphics.Rect
import android.widget.TextView


private val bounds = Rect()

fun String.getWightForTextView(textView: TextView): Int {
    val textPaint: Paint = textView.getPaint()
    textPaint.getTextBounds(this, 0, this.length, bounds)
    return bounds.width()
}
