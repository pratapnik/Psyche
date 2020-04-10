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

        //To go to developer's linkedin profilepage
        iv_linked_in_logo.setOnClickListener {
            var intent :Intent
            intent = Intent()
            intent.action =Intent.ACTION_VIEW
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.setData(Uri.parse("https://www.linkedin.com/in/nikhilpsingh17/"))
            startActivity(intent)
        }

        //To go to developer's Github profilepage
        iv_github_logo.setOnClickListener {
            var intent :Intent
            intent = Intent()
            intent.action =Intent.ACTION_VIEW
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.setData(Uri.parse("https://github.com/pratapnik"))
            startActivity(intent)
        }

        btn_click_here.setOnClickListener {
            var i: Intent
            i = Intent()
            i.action = Intent.ACTION_SEND
            i.setDataAndType(Uri.parse("nikhil.pratap.singh.581@gmail.com"), "message/rfc822")
            i.putExtra(Intent.EXTRA_SUBJECT, "Problem in PSYCHE");


            startActivity(Intent.createChooser(i, "Choose an Email client:"));
        }

    }
}


