package com.first.myapplication.mht

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.firebase.client.Firebase
import com.first.myapplication.mht.utils.isConnectionAvailable
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var firebase: Firebase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Firebase.setAndroidContext(this)
        mAuth = FirebaseAuth.getInstance()

        if (this.isConnectionAvailable()) {
            tvNoInternet.visibility = View.GONE
            btnRefresh.visibility = View.GONE
            val handler = Handler()
            handler.postDelayed({
                val user = mAuth!!.currentUser
                updateUI(user)
            }, 700)
        } else {
            tvNoInternet.visibility = View.VISIBLE
            btnRefresh.visibility = View.VISIBLE
        }
        btnRefresh.setOnClickListener(View.OnClickListener { recreate() })
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val i = Intent(this@SplashActivity, PsychoThemeActivity::class.java)
            startActivity(i)
        } else {
            val i = Intent(this@SplashActivity, GettingStartedActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}