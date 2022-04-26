/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 9/4/2022.
 */

package com.incetro.countype.common.presentation.adapter

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Displayed item in [RecyclerView].
 * Used together with [DataBindingDiffAdapter].
 * [B] is type of DataBinding generated class for your [DataBindingViewItem] layout.
 */
interface DataBindingViewItem<B : ViewDataBinding> {

    @LayoutRes
    fun getLayoutId(): Int

    /**
     * Binds item to [holder].
     */
    fun bind(holder: DataBindingViewHolder<B>)
}
