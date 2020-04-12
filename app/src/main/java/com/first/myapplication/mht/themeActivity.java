package com.first.myapplication.mht;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.first.myapplication.mht.classes.GoogleAccountInfo;
import com.first.myapplication.mht.data.GoogleAccountInfoData;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class themeActivity extends AppCompatActivity implements View.OnClickListener {

    Button timeManagement, anxiety, internet;
    Toolbar toolbarTheme;
    TextView tvGreetingMessage;

    GoogleSignInAccount googleSignInAccount;
    GoogleAccountInfo googleAccountInfo;
    GoogleAccountInfoData googleAccountInfoData;

    private int hourOfTheDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        timeManagement = findViewById(R.id.timeManagement);
        anxiety = findViewById(R.id.anxiety);
        internet = findViewById(R.id.internet);
        toolbarTheme = findViewById(R.id.toolbar_theme);
        tvGreetingMessage = findViewById(R.id.tvGreetingMessage);

        toolbarTheme.inflateMenu(R.menu.mymenu);

        hourOfTheDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        googleSignInAccount = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        googleAccountInfo = new GoogleAccountInfo();

        googleAccountInfoData = googleAccountInfo.getGoogleAccountInfo(googleSignInAccount);

        updateGreetingMessage(googleAccountInfoData, hourOfTheDay);

        Animation animation = AnimationUtils.loadAnimation(themeActivity.this, R.anim.fadein);
        internet.setAnimation(animation);
        timeManagement.setAnimation(animation);
        anxiety.setAnimation(animation);


        internet.setOnClickListener((View.OnClickListener) this);
        timeManagement.setOnClickListener((View.OnClickListener) this);
        anxiety.setOnClickListener((View.OnClickListener) this);

        toolbarTheme.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.profile) {
                    Intent i = new Intent(themeActivity.this, ProfileActivity.class);
                    startActivity(i);
                    return true;
                } else if (menuItem.getItemId() == R.id.about) {
                    Intent i = new Intent(themeActivity.this, AboutUsActivity.class);
                    startActivity(i);
                    return true;
                } else if (menuItem.getItemId() == R.id.scale) {
                    Intent i = new Intent(themeActivity.this, ScaleDisplayActivity.class);
                    startActivity(i);
                    return true;
                } else if (menuItem.getItemId() == R.id.signout) {
                    logoutOrCancel();
                    return true;

                }
                return false;
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
        switch (v.getId()) {

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

    public void logoutOrCancel() {
        View view = getLayoutInflater().inflate(R.layout.jarvis_log_out_alert_dialog, null);
        Button buttonLogout = view.findViewById(R.id.btnLogout);
        Button buttonCancel = view.findViewById(R.id.btnCancel);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(themeActivity.this);
        alertDialogBuilder.setView(view);
        // create and show the alert dialog
        final AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(themeActivity.this, loginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void updateGreetingMessage(GoogleAccountInfoData googleAccountInfoData, int hour){
          String greetingMessage;
          if(hour<12 && hour>=5)
              greetingMessage = "Good Morning, "+ googleAccountInfoData.getDisplayName();
          else if(hour>=12 && hour<16)
              greetingMessage = "Good Afternoon, "+ googleAccountInfoData.getDisplayName();
          else
              greetingMessage = "Good Evening, "+ googleAccountInfoData.getDisplayName();

          tvGreetingMessage.setText(greetingMessage);
    }
}
