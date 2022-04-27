/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 8/4/2022.
 */

package com.incetro.countype.presentation.dialog.inputdialog.view

import com.incetro.countype.common.presentation.base.BaseView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface InputDialogView : BaseView {
    fun dismiss()
}
