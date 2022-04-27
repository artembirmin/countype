/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 8/4/2022.
 */

package com.incetro.countype.presentation.dialog.inputdialog.presenter

import com.incetro.countype.common.presentation.base.basedialog.BaseDialogPresenter
import com.incetro.countype.presentation.dialog.inputdialog.view.InputDialogFragment
import com.incetro.countype.presentation.dialog.inputdialog.view.InputDialogView
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class InputDialogPresenter @Inject constructor() :
    BaseDialogPresenter<InputDialogView, InputDialogFragment.Listeners>() {

    override lateinit var listeners: InputDialogFragment.Listeners

    fun onPositiveButtonClick(text: String) {
        listeners.onPositiveButtonClickListener(text)
        dismissDialog()
    }

    fun onNegativeButtonClick() {
        listeners.onNegativeButtonClickListener()
        dismissDialog()
    }

    private fun dismissDialog() {
        listeners.onDismiss()
        viewState.dismiss()
    }
}
