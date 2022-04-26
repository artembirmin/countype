/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 1/3/2022.
 */

package com.incetro.countype.common.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Simple implementation of [RecyclerView.Adapter].
 * [V] is type of displayed item. It must be [ViewItem] implementation.
 */
abstract class BaseAdapter<V : ViewItem> :
    RecyclerView.Adapter<BaseViewHolder>() {

    /**
     * List of displayed items.
     */
    open var items: MutableList<V> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    /**
     * Adds [item] to top of list.
     */
    fun addItemToTop(item: V) {
        items.add(0, item)
        notifyItemInserted(0)
    }

    /**
     * Removes all items from list.
     */
    fun clearAll() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        return BaseViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int = items[position].getLayoutId()

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        items[holder.bindingAdapterPosition].bind(holder)
    }

    override fun getItemCount(): Int = items.size
}