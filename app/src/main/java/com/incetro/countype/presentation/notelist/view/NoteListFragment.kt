/*
 * My Application
 *
 * Created by artembirmin on 27/4/2022.
 */

package com.incetro.countype.presentation.notelist.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.incetro.countype.R
import com.incetro.countype.common.presentation.base.basefragment.BaseFragment
import com.incetro.countype.databinding.FragmentNoteListBinding
import com.incetro.countype.presentation.dialog.inputdialog.view.InputDialogFragment
import com.incetro.countype.presentation.dialog.inputdialog.view.InputDialogInitParams
import com.incetro.countype.presentation.notelist.adapter.NoteListAdapter
import com.incetro.countype.presentation.notelist.adapter.NoteViewItem
import com.incetro.countype.presentation.notelist.di.component.NoteListComponent
import com.incetro.countype.presentation.notelist.presenter.NoteListPresenter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class NoteListFragment : BaseFragment<FragmentNoteListBinding>(), NoteListView {

    override val layoutRes = R.layout.fragment_note_list

    @Inject
    @InjectPresenter
    lateinit var presenter: NoteListPresenter

    @ProvidePresenter
    fun providePresenter(): NoteListPresenter {
        return presenter
    }

    private val noteListAdapter = NoteListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvNoteList.apply {
            adapter = noteListAdapter
            layoutManager = LinearLayoutManager(context)
        }
        binding.fabAddNote.setOnClickListener { presenter.onAddNoteClick() }
    }

    override fun inject() = NoteListComponent.Manager.getComponent().inject(this)
    override fun release() = NoteListComponent.Manager.releaseComponent()
    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun showNotes(notes: List<NoteViewItem>) {
        noteListAdapter.submitList(notes.toMutableList())
    }

    override fun showAddNoteDialog(onAddClick: (String) -> Unit) {
        InputDialogFragment.newInstance(
            InputDialogInitParams(
                tag = ADD_NOTE_DIALOG_TAG,
                title = R.string.title_folder_name,
                inputHint = R.string.hint_folder_name,
                positiveButtonText = R.string.label_add,
                negativeButtonText = R.string.label_cancel
            ),
            listeners = InputDialogFragment.Listeners(
                onPositiveButtonClickListener = { noteName ->
                    onAddClick(noteName)
                }
            )
        ).show(childFragmentManager)
    }

    companion object {
        /** Creates new fragments instance of [NoteListFragment].*/
        fun newInstance() = NoteListFragment()

        const val ADD_NOTE_DIALOG_TAG = "AddNoteDialog"
    }
}