package com.incetro.phrasescalculator.phraseslist.view

import android.graphics.Paint
import android.graphics.Rect
import android.widget.TextView
import org.w3c.dom.Text


private val bounds = Rect()

private val referenceSymbol = "9"

fun String.getWidhtForTextView(textView: TextView): Int {
    val textPaint: Paint = textView.getPaint()
    textPaint.getTextBounds(this, 0, this.length, bounds)
    return bounds.width()
}

fun TextView.getWidhtOfText(text: String): Int {
    return text.getWidhtForTextView(this)
}

fun TextView.getMaxWidhtOfCountOfSymbols(count: Int): Int{
    return referenceSymbol.repeat(count).getWidhtForTextView(this)
}