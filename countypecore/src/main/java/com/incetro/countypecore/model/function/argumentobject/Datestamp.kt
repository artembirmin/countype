/*
 * My Application
 *
 * Created by artembirmin on 5/5/2023.
 */

package com.incetro.countypecore.model.function.argumentobject

import org.joda.time.DateTime

data class Datestamp(
    val dateFormat: String,
    val date: DateTime? = null,
)