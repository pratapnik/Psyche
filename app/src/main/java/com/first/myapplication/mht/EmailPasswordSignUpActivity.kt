package com.first.myapplication.mht

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_email_password_login.*

class EmailPasswordSignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_password_login)

        val animation = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        clEmailLogin.startAnimation(animation)
    }
}