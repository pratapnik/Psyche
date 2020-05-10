package com.first.myapplication.mht

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_about.*

class AboutUsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        //To go to developer's linkedin profilepage
        btnLinkedInProfile.setOnClickListener {
            var intent: Intent
            intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.setData(Uri.parse("https://www.linkedin.com/in/nikhilpsingh17/"))
            startActivity(intent)
        }

        //To go to developer's Github profilepage
        btnGithubProfile.setOnClickListener {
            var intent: Intent
            intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.setData(Uri.parse("https://github.com/pratapnik"))
            startActivity(intent)
        }

        toolbar_about.setNavigationOnClickListener(View.OnClickListener {
            onBackPressed()
        })
    }
}


