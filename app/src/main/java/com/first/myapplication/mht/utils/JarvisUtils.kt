package com.first.myapplication.mht.utils

import android.app.ProgressDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat.getSystemService
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.google.android.material.snackbar.Snackbar

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

fun View.showSnackBar(snackbarText: String){
    val snackbar = Snackbar.make(this, snackbarText, Snackbar.LENGTH_LONG)
    snackbar.show()
}


