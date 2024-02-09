package com.opqa.gps.permission

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog


fun showDialog(
    context: Context,
    message: String? = null,
    positiveButton: String? = null,
    onPositiveButtonClick: (() -> Unit)? = null,
    negativeButton: String? = null,
) {

    val builder = AlertDialog.Builder(context)
    builder.setMessage(message)
    builder.setPositiveButton(positiveButton , object : DialogInterface.OnClickListener{
        override fun onClick(dialog: DialogInterface?, which: Int) {
            onPositiveButtonClick?.invoke()
            dialog?.dismiss()
        }
    })
    builder.setNegativeButton(negativeButton , object : DialogInterface.OnClickListener{
        override fun onClick(dialog: DialogInterface?, which: Int) {
            dialog?.dismiss()
        }
    })


}