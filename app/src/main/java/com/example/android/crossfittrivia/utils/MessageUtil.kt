package com.example.android.crossfittrivia.utils

import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.example.android.crossfittrivia.R

class MessageUtil {

    companion object {
        fun makeToast(text: String, activity: FragmentActivity) {
            Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
        }

        fun backButtonDialog(context: Context, dialogClickListener: DialogInterface.OnClickListener) {
            AlertDialog.Builder(context, R.style.CustomDialogStyle).setMessage(context.getString(R.string.back_button_ask)).setPositiveButton(
                context.getString(R.string.yes),
                dialogClickListener
            )
                .setNegativeButton(R.string.no, dialogClickListener).show()
        }
    }
}