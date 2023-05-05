/*
 * Created by Artem Petrosyan on 28/10/2021.
 *
 */

package com.incetro.countypecore.model.returnablevalue

class FormattedPair(private val first: FormattedValue, private val second: String) :
    FormattedValue {

    constructor(first: FormattedValue, second: FormattedValue) : this(
        first,
        second.toString()
    )

    override fun toString(): String {
        return first.toString() + second.toString()
    }
}