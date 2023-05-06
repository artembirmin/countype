/*
 * My Application
 *
 * Created by artembirmin on 6/5/2023.
 */

package com.incetro.countype.data.database.timestamp

import androidx.room.Dao
import androidx.room.Query
import com.incetro.countype.data.database.BaseDao
import io.reactivex.rxjava3.core.Single

@Dao
interface TimestampDao : BaseDao<TimestampDto> {

    @Query("SELECT * FROM ${TimestampDto.TABLE_NAME}")
    fun getAll(): Single<List<TimestampDto>>
}