/*
 * My Application
 *
 * Created by artembirmin on 6/5/2023.
 */

package com.incetro.countype.data.database.measure

import androidx.room.Dao
import androidx.room.Query
import com.incetro.countype.data.database.BaseDao
import io.reactivex.rxjava3.core.Single

@Dao
interface MeasureDao : BaseDao<MeasureDto> {

    @Query("SELECT * FROM ${MeasureDto.TABLE_NAME}")
    fun getAll(): Single<List<MeasureDto>>
}