package com.incetro.phrasescalculator.model

data class Phrase(
    val id: String,
    var position: Int,
    var expression: String = "",
    var selectionStartPosition: Int = 0
)