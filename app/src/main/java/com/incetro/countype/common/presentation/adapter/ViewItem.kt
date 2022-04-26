/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 1/3/2022.
 */

package com.incetro.countype.common.presentation.adapter

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * Displayed item in [RecyclerView].
 */
interface ViewItem {

    @LayoutRes
    fun getLayoutId(): Int

    /**
     * Binds item to [holder].
     */
    fun bind(holder: BaseViewHolder)
}
