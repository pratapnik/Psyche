package com.first.myapplication.mht

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_about.*

class AboutUsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        b.setOnClickListener { onBackPressed() }

        iv_linked_in_logo.setOnClickListener {
            var intent :Intent
            intent = Intent()
            intent.action =Intent.ACTION_VIEW
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.setData(Uri.parse("https://www.linkedin.com/in/nikhilpsingh17/"))
            startActivity(intent)
        }

    }
}


