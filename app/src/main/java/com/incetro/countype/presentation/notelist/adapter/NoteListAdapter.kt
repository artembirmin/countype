/*
 * My Application
 *
 * Created by artembirmin on 27/4/2022.
 */

package com.incetro.countype.presentation.notelist.adapter

import com.incetro.countype.common.presentation.adapter.DataBindingDiffAdapter
import com.incetro.countype.databinding.ListItemNoteBinding

class NoteListAdapter :
    DataBindingDiffAdapter<ListItemNoteBinding, NoteViewItem>(NoteViewItem.DIFF_UTIL_ITEM_CALLBACK)