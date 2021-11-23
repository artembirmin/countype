/*
 * Created by Artem Petrosyan on 28/10/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
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