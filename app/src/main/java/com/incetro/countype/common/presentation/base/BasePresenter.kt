/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 19/2/2022.
 */

package com.incetro.countype.common.presentation.base


import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

/**
 * Contains basic functionality for [Fragment] presenter.
 */
abstract class BasePresenter<V : BaseView> : MvpPresenter<V>() {

    /**
     * [CompositeDisposable] instance.
     */
    private val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        compositeDisposable.dispose()
    }

    open fun onBackPressed() {
    }

    /**
     * Adding [Disposable] in [CompositeDisposable].
     */
    protected fun Disposable.addDisposable(): Disposable {
        compositeDisposable.add(this)
        return this
    }
}