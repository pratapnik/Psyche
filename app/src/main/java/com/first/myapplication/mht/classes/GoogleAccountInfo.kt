package com.first.myapplication.mht.classes

import com.first.myapplication.mht.data.GoogleAccountInfoData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

class GoogleAccountInfo {

    fun getGoogleAccountInfo(googleAccountInfoObject : GoogleSignInAccount): GoogleAccountInfoData {
        return googleAccountInfoObject.displayName?.let {
            googleAccountInfoObject.email?.let { it1 ->
                googleAccountInfoObject.photoUrl?.let { it2 ->
                    googleAccountInfoObject.givenName?.let {
                        it3 ->
                        GoogleAccountInfoData(it, it1,
                                it2, it3)
                    }

                }
            }
        }!!
    }
}