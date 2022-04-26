/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 8/4/2022.
 */

package com.incetro.countype.common.presentation.base.basedialog

import androidx.fragment.app.DialogFragment
import com.incetro.countype.common.presentation.base.BaseView
import moxy.MvpPresenter

/**
 * Contains basic functionality for [DialogFragment] presenter.
 */
abstract class BaseDialogPresenter<V : BaseView, L : DialogListeners> : MvpPresenter<V>() {
    /**
     * Instance of [DialogListeners] implementation.
     */
    abstract val listeners: L
}