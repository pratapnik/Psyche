package com.first.myapplication.mht;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    ConstraintLayout constraintLayoutSplash;

    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Firebase.setAndroidContext(this);

        constraintLayoutSplash = findViewById(R.id.cl_splash);
        Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.slide_up);
        constraintLayoutSplash.setAnimation(animation);

        mAuth= FirebaseAuth.getInstance();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser user = mAuth.getCurrentUser();
                updateUI(user);
            }
        },1500);

    }
    public void updateUI(FirebaseUser user){
        if(user != null){
            Intent i = new Intent(SplashActivity.this, themeActivity.class);
            startActivity(i);
        }
        else{
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}
