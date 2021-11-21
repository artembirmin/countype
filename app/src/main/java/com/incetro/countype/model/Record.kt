package com.incetro.countype.model

data class Record(
    val id: String,
    var position: Int,
    var phrase: String = "",
    var answer: String
)