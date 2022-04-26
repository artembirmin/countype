/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 23/3/2022.
 */

package com.incetro.countype.common.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment

/**
 * Wraps parameters for fragment initialization. Saves in [Fragment.mArguments].
 */
interface InitParams : Parcelable

/**
 * Saves init params in [bundle].
 * The key is `SomeFragmentInitParams::class.java.simpleName`.
 */
fun <T : InitParams> T.saveToBundle(bundle: Bundle) {
    val key = this::class.java.simpleName
    bundle.putParcelable(key, this)
}

/**
 * Returns init params whose class is [T].
 */
inline fun <reified T : InitParams> Fragment.getInitParams(): T {
    val key = T::class.java.simpleName
    return arguments?.getParcelable(key) ?: error("$key wasn't set")
}

/**
 * Saves init params to [Fragment.mArguments].
 */
fun <T : InitParams> Fragment.saveInitParams(openParams: T?): Fragment {
    return this.apply {
        arguments = Bundle().apply {
            openParams?.saveToBundle(this)
        }
    }
}