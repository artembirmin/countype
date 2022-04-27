/*
 * My Application
 *
 * Created by artembirmin on 27/4/2022.
 */

package com.incetro.countype.presentation.notelist.view

import com.incetro.countype.common.presentation.base.BaseView
import com.incetro.countype.presentation.notelist.adapter.NoteViewItem
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface NoteListView : BaseView {
    fun showNotes(notes: List<NoteViewItem>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showAddNoteDialog(onAddClick: (String) -> Unit)
}