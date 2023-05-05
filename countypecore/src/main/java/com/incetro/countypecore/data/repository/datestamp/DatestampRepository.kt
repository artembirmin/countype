/*
 * My Application
 *
 * Created by artembirmin on 5/5/2023.
 */

package com.incetro.countypecore.data.repository.datestamp

import com.incetro.countypecore.model.function.argumentobject.Datestamp


interface DatestampRepository {

    fun findDatestampByDate(dateStr: String): Datestamp?

    fun addAll(datestamps: List<Datestamp>)
}
