package com.first.myapplication.mht.utils

import android.app.ProgressDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
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

fun Context.isConnectionAvailable(): Boolean{

    var connected = false
    try {
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nInfo = cm.activeNetworkInfo
        connected = nInfo != null && nInfo.isAvailable && nInfo.isConnected
        return connected
    } catch (e: Exception) {
        Log.e("Connectivity Exception", e.message)
    }
    return connected

}


