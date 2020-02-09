package com.heady.ecommerceapp.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.heady.ecommerceapp.R


/**
 * Performs common utility operations.
 */
object Util {

    /**
     * Hide the soft keyboard from screen for edit text only
     *
     * @param context context of current visible activity
     * @param view    clicked view
     */
    fun hideSoftKeyBoard(context: Activity?, view: View) {
        try {
            context.let {
                val imm =
                    context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showSoftKeyBoard(context: Context, view: View) {
        try {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            imm?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    /**
     * Displays alert dialog to show common messages.
     *
     * @param title   Title of the Dialog : Application's Name
     * @param message Message to be shown in the Dialog displayed
     * @param context Context of the Application, where the Dialog needs to be displayed
     */
    fun displayDialog(context: Context, title: String, message: String) {
        var message = message
        if (TextUtils.isEmpty(message)) {
            message = context.getString(R.string.alert_message_error)
        }

        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setTitle(title)
        alertDialog.setCancelable(false)

        alertDialog.setMessage(message)
        alertDialog.setButton(
            AlertDialog.BUTTON_POSITIVE,
            context.getString(android.R.string.ok)
        ) { dialog, which -> dialog.dismiss() }

        if (!(context as Activity).isFinishing) {
            alertDialog.show()
        }
    }

}
