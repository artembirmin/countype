/*
 * Created by Artem Petrosyan on 9/9/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
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
