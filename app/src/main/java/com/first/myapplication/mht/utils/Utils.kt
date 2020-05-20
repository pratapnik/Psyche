package com.first.myapplication.mht.utils

import android.app.ProgressDialog
import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

fun showProgressDialogWithTitle(substring: String?, progressDialog: ProgressDialog) {
    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
    progressDialog.setCancelable(false)
    progressDialog.setMessage(substring)
    progressDialog.show()
}

fun hideProgressDialogWithTitle(progressDialog: ProgressDialog) {
    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
    progressDialog.dismiss()
}

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}
