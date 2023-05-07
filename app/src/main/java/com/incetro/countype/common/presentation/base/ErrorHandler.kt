/*
 * My Application
 *
 * Created by artembirmin on 6/5/2023.
 */

package com.incetro.countype.common.presentation.base

import android.app.AlertDialog
import android.content.Context
import androidx.annotation.StringRes
import com.google.gson.Gson
import com.incetro.countype.R
import com.incetro.countype.common.navigation.AppRouter
import com.incetro.countype.presentation.toReadableLogDateTime
import org.joda.time.DateTime
import retrofit2.HttpException
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.net.ssl.SSLHandshakeException


class ErrorHandler @Inject constructor(
    private val appRouter: AppRouter,
) {

    private val gson = Gson()

    fun showError(error: Throwable, context: Context) {
        Timber.e(error)
        when (error) {
            is UnknownHostException,
            is ConnectException,
            is SSLHandshakeException,
            is SocketTimeoutException,
            -> showBaseErrorMessage(
                R.string.error_common_text,
                context
            )

            is HttpException -> {
                showBaseErrorMessage(context.getString(R.string.error_common_text), context)
            }
            else -> {
                showBaseErrorMessage(R.string.error_common_text, context)
            }
        }
    }

    private fun showBaseErrorMessage(
        @StringRes message: Int,
        context: Context,
    ) {
        val messageText = context.getString(message)
        showBaseErrorMessage(messageText, context)
    }

    private fun showBaseErrorMessage(
        message: String,
        context: Context,
    ) {
        showMessageByDialog(
            message = message,
            context = context
        )
    }

    private fun showMessageByDialog(
        context: Context,
        message: CharSequence = "",
    ) {
        AlertDialog.Builder(context)
            .setMessage(message)
            .setPositiveButton(R.string.alert_button_ok) { _, _ -> }
            .create().show()
    }

    private fun showDebugMessageByDialog(
        context: Context,
        message: CharSequence = "",
        errorDate: String = DateTime.now().toReadableLogDateTime(),
    ) {
        AlertDialog.Builder(context)
            .setTitle(errorDate)
            .setMessage(message)
            .setPositiveButton(R.string.alert_button_ok) { _, _ -> }
            .create().show()
    }
}