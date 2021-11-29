/*
 * Created by Artem Petrosyan on 27/10/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.di

import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

/**
 * DI factory. Создает и отдает экземпляры классов. Хранит их в контейнере [instances].
 */
internal abstract class DiFactory {

    /**
     * Хранилище, контейнер экземпляров классов [KClass].
     */
    private val instances: HashMap<KClass<*>, Any> = hashMapOf()

    /**
     * Create and return instance of [clazz] use [argsForConstructor].
     * If instance is already exists, returns instance from [instances].
     * @return instance of [clazz].
     */
    protected fun getInstance(clazz: KClass<*>, vararg argsForConstructor: Any): Any {
        return instances[clazz]
            ?: clazz.primaryConstructor!!
                .call(*argsForConstructor)
                .also { instances[clazz] = it }
    }
}
