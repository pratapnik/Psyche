package com.first.myapplication.mht;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

public class profile extends AppCompatActivity {

    TextView name, email, textViewLocation;
    ImageView photo;

    LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.tv_user_name);
        email = findViewById(R.id.tv_email);
        photo = findViewById(R.id.iv_profile_photo);
        textViewLocation = findViewById(R.id.tv_location_data);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (acct != null) {
            String person = acct.getGivenName();
            String lastname = acct.getFamilyName();
            String personEmail = acct.getEmail();
            Uri personPhoto = acct.getPhotoUrl();
            Picasso.with(getApplicationContext()).load(personPhoto).into(photo);
            name.setText(person + " " + lastname);
            email.setText(personEmail);
        }

        LinearLayout layout = findViewById(R.id.linearlayout_profile);
        Animation animation = AnimationUtils.loadAnimation(profile.this, R.anim.lefttoright);
        layout.startAnimation(animation);




    }

}
