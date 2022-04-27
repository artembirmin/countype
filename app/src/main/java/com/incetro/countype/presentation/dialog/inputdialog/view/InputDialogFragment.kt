/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 8/4/2022.
 */

package com.incetro.countype.presentation.dialog.inputdialog.view

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.StringRes
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.incetro.countype.R
import com.incetro.countype.common.navigation.getInitParams
import com.incetro.countype.common.navigation.saveInitParams
import com.incetro.countype.common.presentation.base.basedialog.BaseDialogFragment
import com.incetro.countype.common.presentation.base.basedialog.DialogListeners
import com.incetro.countype.databinding.DialogInputBinding
import com.incetro.countype.presentation.dialog.inputdialog.di.InputDialogComponent
import com.incetro.countype.presentation.dialog.inputdialog.presenter.InputDialogPresenter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class InputDialogFragment : BaseDialogFragment<DialogInputBinding>(), InputDialogView {

    /** [InputDialogFragment.Listeners] instance. */
    private lateinit var listeners: Listeners

    /** [InputDialogInitParams] instance. */
    private val openParams: InputDialogInitParams by lazy { getInitParams() }

    /** [InputDialogFragment.Content] instance. */
    private val content: Content by lazy {
        with(openParams) {
            Content(title, inputHint, positiveButtonText, negativeButtonText)
        }
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: InputDialogPresenter

    @ProvidePresenter
    fun providePresenter(): InputDialogPresenter {
        presenter.listeners = listeners
        return presenter
    }

    override val layoutRes = R.layout.dialog_input

    override fun inject() = InputDialogComponent.Manager.getComponent().inject(this)
    override fun release() = InputDialogComponent.Manager.releaseComponent()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInputField()
        setListeners()
        setupKeyboard()
    }

    private fun setupInputField() {
        with(binding) {
            content = this@InputDialogFragment.content
            etInputField.requestFocus()
            etInputField.doOnTextChanged { text, _, _, _ ->
                btnPositive.isEnabled = text?.isNotEmpty() ?: false
            }
        }
    }

    /**
     * Sets listeners to buttons.
     */
    private fun setListeners() {
        with(binding) {
            btnPositive.setOnClickListener {
                presenter.onPositiveButtonClick(etInputField.text.toString())
            }
            btnNegative.setOnClickListener {
                presenter.onNegativeButtonClick()
            }
        }
    }

    private fun setupKeyboard() {
        requireParentFragment().requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
        requireDialog().window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    /**
     * Display the dialog, adding the fragment to the given [fragmentManager].
     * Calls [DialogFragment.show] with [fragmentManager] and [InputDialogInitParams.tag].
     */
    fun show(fragmentManager: FragmentManager) {
        show(fragmentManager, openParams.tag)
    }

    /**
     * Listeners for [InputDialogFragment].
     */
    class Listeners(
        override val onPositiveButtonClickListener: (String) -> Unit = {},
        override val onNegativeButtonClickListener: () -> Unit = {},
        override val onDismiss: () -> Unit = {}
    ) : DialogListeners

    /**
     * Content displayed in the dialog.
     * Contains the identifiers of of displayed string resources.
     */
    class Content(
        /**
         * Displayed in title TextView of dialog.
         */
        @StringRes val title: Int,
        /**
         * Displayed as hint in input EditText of dialog.
         */
        @StringRes val inputHint: Int,
        /**
         * Displayed in positive button of dialog.
         */
        @StringRes val positiveButtonText: Int,
        /**
         * Displayed in negative button of dialog.
         */
        @StringRes val negativeButtonText: Int
    )

    companion object {

        /**
         * Returns instance of [InputDialogFragment].
         * @param openParams is parameters for dialog initialization.
         * @param listeners is listeners for dialog buttons.
         */
        fun newInstance(
            openParams: InputDialogInitParams,
            listeners: Listeners
        ): InputDialogFragment {
            return (InputDialogFragment().saveInitParams(openParams)
                    as InputDialogFragment)
                .apply { this.listeners = listeners }
        }
    }
}