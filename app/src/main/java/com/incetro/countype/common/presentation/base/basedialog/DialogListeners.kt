/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 3/4/2022.
 */

package com.incetro.countype.common.presentation.base.basedialog

/**
 * Listeners for dialogs.
 */
interface DialogListeners {
    /**
     * Called when positive button is clicked.
     */
    val onPositiveButtonClickListener: Function<Unit>

    /**
     * Called when negative button is clicked.
     */
    val onNegativeButtonClickListener: Function<Unit>

    /**
     * Called after clicked any button and before closing dialog.
     */
    val onDismiss: Function<Unit>
}