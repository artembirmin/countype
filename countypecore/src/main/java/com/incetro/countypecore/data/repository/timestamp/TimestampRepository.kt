/*
 * My Application
 *
 * Created by artembirmin on 5/5/2023.
 */

package com.incetro.countypecore.data.repository.timestamp

import com.incetro.countypecore.model.function.argumentobject.Timestamp


interface TimestampRepository {

    fun findTimestampByTime(time: String): Timestamp?

    fun addAll(timestamps: List<Timestamp>)
}
