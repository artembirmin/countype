/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 16/2/2022.
 */

package com.incetro.countype.app.componentmanager

/**
 * Provides Dagger component.
 */
interface ComponentProvider {

    /**
     * Returns [clazz] instance. [clazz] is Dagger component class.
     */
    fun <T> getComponent(clazz: Class<T>): T
}