package com.first.myapplication.mht

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_email_password_signup.*

class EmailPasswordSignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_password_signup)

        val animation = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        clEmailSignup.startAnimation(animation)

        btn_sign_in_email.setOnClickListener {
            val loginEmailIntent = Intent(this, EmailPasswordLoginActivity::class.java)
            startActivity(loginEmailIntent)
            finish()
        }
    }
}