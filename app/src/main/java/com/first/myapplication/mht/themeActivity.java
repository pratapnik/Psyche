package com.first.myapplication.mht;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class themeActivity extends AppCompatActivity implements View.OnClickListener {

    Button timeManagement,anxiety,internet;
    FloatingActionButton signout;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        timeManagement = findViewById(R.id.timeManagement);
        anxiety = findViewById(R.id.anxiety);
        internet = findViewById(R.id.internet);

        bottomNavigationView = findViewById(R.id.bottom);

        Animation animation = AnimationUtils.loadAnimation(themeActivity.this, R.anim.righttoleft);
        internet.setAnimation(animation);
        timeManagement.setAnimation(animation);
        anxiety.setAnimation(animation);


        internet.setOnClickListener((View.OnClickListener) this);
        timeManagement.setOnClickListener((View.OnClickListener) this);
        anxiety.setOnClickListener((View.OnClickListener) this);

        signout = findViewById(R.id.signout);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.profile:

                        Intent i = new Intent(themeActivity.this,profile.class);
                        startActivity(i);
                        return true;
                    case R.id.scale:
                        Intent in = new Intent(themeActivity.this,scale.class);
                        startActivity(in);
                        return true;
                    case R.id.about:
                        Intent inte = new Intent(themeActivity.this, AboutUsActivity.class);
                        startActivity(inte);
                        return true;
//                    case R.id.nav_notifications:
//                        return true;
                }
                return false;
            }

        });


        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(themeActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });


    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
    @Override
    public void onClick(View v) {
        Intent i = new Intent(themeActivity.this, question.class);
        switch(v.getId()) {

            case R.id.internet:
                i.putExtra("type", 0);
                startActivity(i);
                break;
            case R.id.timeManagement:
                i.putExtra("type", 1);
                startActivity(i);
                break;
            case R.id.anxiety:
                i.putExtra("type", 2);
                startActivity(i);
                break;
        }
    }




}
