package com.first.myapplication.mht;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

public class profile extends AppCompatActivity {

    TextView name, email;
    ImageView photo, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        photo = findViewById(R.id.photo);
        back = findViewById(R.id.b);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(acct!=null){
            String person = acct.getGivenName();
            String lastname = acct.getFamilyName();
            String personEmail = acct.getEmail();
            Uri personPhoto = acct.getPhotoUrl();
            Picasso.with(getApplicationContext()).load(personPhoto).into(photo);
            name.setText(person+" "+lastname);
            email.setText(personEmail);
        }
    }
}
