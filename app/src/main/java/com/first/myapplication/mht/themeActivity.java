package com.first.myapplication.mht;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.first.myapplication.mht.widgets.ProfilePopupDialog;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class themeActivity extends AppCompatActivity implements View.OnClickListener {

    Button timeManagement, anxiety, internet;
    Toolbar toolbarTheme;
    TextView tvGreetingMessage;

    GoogleSignInAccount googleSignInAccount;
    ImageView ivCloseCovidBar;
    ConstraintLayout clCovid19;

    private int hourOfTheDay;

    private ProfilePopupDialog profilePopupDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        timeManagement = findViewById(R.id.timeManagement);
        anxiety = findViewById(R.id.anxiety);
        internet = findViewById(R.id.internet);
        toolbarTheme = findViewById(R.id.toolbar_theme);
        tvGreetingMessage = findViewById(R.id.tvGreetingMessage);
        ivCloseCovidBar = findViewById(R.id.ivCovidClose);
        clCovid19 = findViewById(R.id.clCovid19);

        profilePopupDialog = new ProfilePopupDialog();
        Date calendarDate = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        String formattedDate = dateFormat.format(calendarDate);
        String formattedDay = dayFormat.format(calendarDate);

        String welcomeMessage = updateGreetingMessage(hourOfTheDay);

        tvGreetingMessage.setText(welcomeMessage + "\nIt's " + formattedDate + ", " + formattedDay);

        toolbarTheme.inflateMenu(R.menu.mymenu);

        hourOfTheDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        googleSignInAccount = GoogleSignIn.getLastSignedInAccount(getApplicationContext());

        Animation animation = AnimationUtils.loadAnimation(themeActivity.this, R.anim.fadein);
        internet.setAnimation(animation);
        timeManagement.setAnimation(animation);
        anxiety.setAnimation(animation);

        ivCloseCovidBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clCovid19.setVisibility(View.GONE);
            }
        });

        clCovid19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent covidIntent = new Intent(themeActivity.this, Covid.class);
                startActivity(covidIntent);
            }
        });

        internet.setOnClickListener((View.OnClickListener) this);
        timeManagement.setOnClickListener((View.OnClickListener) this);
        anxiety.setOnClickListener((View.OnClickListener) this);

        toolbarTheme.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.profile) {
                    openProfileDialog();
                    return true;
                } else if (menuItem.getItemId() == R.id.about) {
                    Intent i = new Intent(themeActivity.this, AboutUsActivity.class);
                    startActivity(i);
                    return true;
                } else if (menuItem.getItemId() == R.id.scale) {
                    Intent i = new Intent(themeActivity.this, ScaleDisplayActivity.class);
                    startActivity(i);
                    return true;
                } else if (menuItem.getItemId() == R.id.menuItemExercise) {
                    Intent i = new Intent(themeActivity.this, ExercisesActivity.class);
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

    private String updateGreetingMessage(int hour) {
        String greetingMessage;
        if (hour < 12 && hour >= 5)
            greetingMessage = "Hey, Good Morning";
        else if (hour >= 12 && hour < 16)
            greetingMessage = "Hey, Good Afternoon";
        else
            greetingMessage = "Hey, Good Evening";

        return greetingMessage;
    }

    public void openProfileDialog(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        profilePopupDialog.show(
                fragmentTransaction,
                getResources().getString(R.string.label_profile_popup_dialog)
        );
    }


//    public Boolean updateCovidLayout() {
//        final boolean[] isCovidVisible = {false};
//        firebase = new Firebase("https://mental-health-tracker-bb023.firebaseio.com/covidFlag");
//        firebase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String covidFlag = dataSnapshot.getValue(String.class);
//                if (covidFlag.equals("1")) {
//                    isCovidVisible[0] = true;
//                } else {
//                    isCovidVisible[0] = false;
//                }
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//        return isCovidVisible[0];
//    }
//

}
