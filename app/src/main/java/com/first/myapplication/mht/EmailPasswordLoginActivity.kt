package com.first.myapplication.mht

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_email_password_login.*
import kotlinx.android.synthetic.main.activity_email_password_login.clEmailLogin

class EmailPasswordLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_password_login)

        val animation = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        clEmailLogin.startAnimation(animation)

        btnSignUp.setOnClickListener {
            val signUpEmailIntent = Intent(this, EmailPasswordSignUpActivity::class.java)
            startActivity(signUpEmailIntent)
            finish()
        }

        ivBackLogin.setOnClickListener {
            onBackPressed()
        }
    }
}
