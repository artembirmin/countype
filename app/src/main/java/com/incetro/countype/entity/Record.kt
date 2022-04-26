/*
 * My Application
 *
 * Created by artembirmin on 26/4/2022.
 */

package com.incetro.countype.entity

data class Record(
    val id: String,
    var position: Int,
    var phrase: String = "",
    var answer: String
)