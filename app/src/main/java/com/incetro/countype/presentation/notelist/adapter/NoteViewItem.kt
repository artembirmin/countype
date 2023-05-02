/*
 * My Application
 *
 * Created by artembirmin on 27/4/2022.
 */

package com.incetro.countype.presentation.notelist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.incetro.countype.R
import com.incetro.countype.common.presentation.adapter.DataBindingViewHolder
import com.incetro.countype.common.presentation.adapter.DataBindingViewItem
import com.incetro.countype.databinding.ListItemNoteBinding
import com.incetro.countype.entity.Note
import java.text.SimpleDateFormat
import java.util.*

class NoteViewItem(
    val note: Note,
    private val onSelectListener: (note: Note) -> Unit,
) : DataBindingViewItem<ListItemNoteBinding> {
    override fun getLayoutId(): Int = R.layout.list_item_note

    override fun bind(holder: DataBindingViewHolder<ListItemNoteBinding>) {
        holder.binding.note = note
        holder.binding.root.setOnClickListener { onSelectListener(note) }
        holder.binding.tvTime.text =
            SimpleDateFormat("HH:mm", Locale.getDefault()).format(note.lastUpdateTime)
        holder.binding.tvDate.text =
            SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(note.lastUpdateTime)
    }

    companion object {
        val DIFF_UTIL_ITEM_CALLBACK = object : DiffUtil.ItemCallback<NoteViewItem>() {
            override fun areItemsTheSame(oldItem: NoteViewItem, newItem: NoteViewItem): Boolean {
                return oldItem.note.id == newItem.note.id
            }

            override fun areContentsTheSame(oldItem: NoteViewItem, newItem: NoteViewItem): Boolean {
                return oldItem.note.name == newItem.note.name
                        && oldItem.note.lastUpdateTime == newItem.note.lastUpdateTime
                        && oldItem.note.records.first() == newItem.note.records.first()
            }
        }
    }
}