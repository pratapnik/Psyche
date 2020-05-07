package com.first.myapplication.mht.widgets

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.first.myapplication.mht.R
import com.google.firebase.auth.FirebaseAuth

class ProfilePopupDialog : DialogFragment() {
    private lateinit var profilePopupDialog: AlertDialog.Builder
    private lateinit var btnOk: TextView

    private lateinit var userEmail: String
    private lateinit var tvUserEmail: TextView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var layoutInflater: LayoutInflater? = activity?.layoutInflater
        val view = layoutInflater?.inflate(R.layout.jarvis_profile_popup_dialog, null)

        profilePopupDialog = AlertDialog.Builder(activity)
        profilePopupDialog.setView(view)

        btnOk = view?.findViewById(R.id.tvOk)!!
        tvUserEmail = view?.findViewById(R.id.tvPopupEmail)

        val firebaseUser = FirebaseAuth.getInstance().currentUser

        tvUserEmail.text = firebaseUser?.email

        btnOk.setOnClickListener {
            dismiss()
        }

        return profilePopupDialog.create()
    }
}