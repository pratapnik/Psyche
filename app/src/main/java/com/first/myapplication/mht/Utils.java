package com.first.myapplication.mht;

import android.app.ProgressDialog;

public class Utils {
    public static void showProgressDialogWithTitle(String substring, ProgressDialog progressDialog) {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //Without this user can hide loader by tapping outside screen
        progressDialog.setCancelable(false);
        progressDialog.setMessage(substring);
        progressDialog.show();
    }

    public static void hideProgressDialogWithTitle(ProgressDialog progressDialog) {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.dismiss();
    }
}
