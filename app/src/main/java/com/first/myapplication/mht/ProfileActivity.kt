package com.first.myapplication.mht

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.first.myapplication.mht.classes.GoogleAccountInfo
import com.first.myapplication.mht.data.GoogleAccountInfoData
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {

    lateinit var googleAccountInfoObject : GoogleSignInAccount
    lateinit var googleAccountInfo: GoogleAccountInfo
    lateinit var googleAccountInfoData: GoogleAccountInfoData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        googleAccountInfoObject = GoogleSignIn.getLastSignedInAccount(this)!!

        googleAccountInfo = GoogleAccountInfo()
        googleAccountInfoData = googleAccountInfo.getGoogleAccountInfo(googleAccountInfoObject)!!

        setUserDetails(googleAccountInfoData)

        toolbar_profile.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    fun setUserDetails(googleSignInAccount: GoogleAccountInfoData){
        if(googleSignInAccount!=null){
            val profileFirstName = googleSignInAccount.displayName
            val profileEmail = googleSignInAccount.email
            val profilePhotoUrl = googleSignInAccount.photoUrl

            Picasso.with(this).load(profilePhotoUrl).into(ivProfilePhoto)

            tvProfileName.text = profileFirstName
            tvProfileEmail.text = profileEmail
        }

        else
            Toast.makeText(this, "Profile info cannot be fetched", Toast.LENGTH_LONG).show()
    }
}