package com.first.myapplication.mht

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {

    var profileDisplayDetails = "You are Logged in as"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val firebaseUser = FirebaseAuth.getInstance().currentUser

        profileDisplayDetails = profileDisplayDetails.plus(" ").plus(firebaseUser?.email)

        tvProfileEmail.text = profileDisplayDetails
        tvProfileEmail.setTextColor(resources.getColor(R.color.textColor))

        toolbar_profile.setNavigationOnClickListener {
            onBackPressed()
        }

    }
}