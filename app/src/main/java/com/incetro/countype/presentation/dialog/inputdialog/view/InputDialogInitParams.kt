/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 8/4/2022.
 */

package com.incetro.countype.presentation.dialog.inputdialog.view

import androidx.annotation.StringRes
import com.incetro.countype.common.navigation.InitParams
import kotlinx.parcelize.Parcelize

@Parcelize
data class InputDialogInitParams(
    /**
     * Tag for this dialog fragment.
     */
    val tag: String,
    /**
     * The identifier of the string resource
     * to be displayed in title TextView of dialog.
     */
    @StringRes val title: Int,
    /**
     * The identifier of the string resource
     * to be displayed as hint in input EditText of dialog.
     */
    @StringRes val inputHint: Int,
    /**
     * The identifier of the string resource
     * to be displayed in positive button of dialog.
     */
    @StringRes val positiveButtonText: Int,
    /**
     * The identifier of the string resource
     * to be displayed in negative button of dialog.
     */
    @StringRes val negativeButtonText: Int
) : InitParams