/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 22/2/2022.
 */

package com.incetro.countype.data.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import io.reactivex.rxjava3.core.Completable

interface BaseDao<T> {

    /**
     * Insert an object in the database.
     *
     * @param obj the object to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(obj: T): Completable

    /**
     * Insert an array of objects in the database.
     *
     * @param obj the objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg obj: T): Completable

    /**
     * Insert a list of objects in the database.
     *
     * @param objectsList the objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(objectsList: List<T>): Completable

    /**
     * Update an object from the database.
     *
     * @param obj the object to be updated
     */
    @Update
    fun update(obj: T): Completable

    /**
     * Update a list of objects from the database.
     *
     * @param objectsList the objects to be updated.
     */
    @Update
    fun updateAll(objectsList: List<T>): Int

    /**
     * Delete an object from the database
     *
     * @param obj the object to be deleted
     */
    @Delete
    fun delete(obj: T): Completable
}