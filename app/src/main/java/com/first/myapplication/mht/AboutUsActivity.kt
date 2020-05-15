package com.first.myapplication.mht

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.firebase.client.DataSnapshot
import com.firebase.client.Firebase
import com.firebase.client.FirebaseError
import com.firebase.client.ValueEventListener
import com.first.myapplication.mht.utils.getProgressDrawable
import kotlinx.android.synthetic.main.activity_about.*

class AboutUsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val aboutUsImageUrl = "https://mental-health-tracker-bb023.firebaseio.com/images/about"
        val firebase = Firebase(aboutUsImageUrl)

        firebase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val url = dataSnapshot.getValue(String::class.java)
                Glide.with(applicationContext).load(url).centerCrop().placeholder(getProgressDrawable(iv_about.context)).into(iv_about);
            }

            override fun onCancelled(firebaseError: FirebaseError) {
                Toast.makeText(applicationContext, "There is some internet error", Toast.LENGTH_LONG).show()
            }
        })

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


