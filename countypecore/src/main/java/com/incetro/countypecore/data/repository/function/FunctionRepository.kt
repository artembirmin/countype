/*
 * Created by Artem Petrosyan on 9/9/2021.
 *
 */

package com.incetro.countypecore.data.repository.function

import com.incetro.countypecore.model.function.Function

/**
 * Репозиторий для работы с хранилищем [Function].
 */
interface FunctionRepository {

    /**
     * Осуществляет поиск [Function] по [id].
     * @throws IllegalStateException if function with [id] not found.
     */
    fun findFunctionById(id: String): Function

    /**
     * Добавляет экземпляр [Function].
     */
    // в курсовой об этом методе не сказано
    fun add(function: Function)
}
