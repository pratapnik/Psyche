package com.first.myapplication.mht;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.firebase.client.Firebase;
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

        mAuth = FirebaseAuth.getInstance();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser user = mAuth.getCurrentUser();
                updateUI(user);
            }
        }, 1500);

    }

    public void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent i = new Intent(SplashActivity.this, themeActivity.class);
            startActivity(i);
        } else {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}
