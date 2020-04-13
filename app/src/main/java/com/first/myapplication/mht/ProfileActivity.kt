package com.first.myapplication.mht

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val firebaseUser = FirebaseAuth.getInstance().currentUser
        tvProfileEmail.text = firebaseUser?.email
        tvProfileName.text = firebaseUser?.displayName

        toolbar_profile.setNavigationOnClickListener {
            onBackPressed()
        }

    }
}