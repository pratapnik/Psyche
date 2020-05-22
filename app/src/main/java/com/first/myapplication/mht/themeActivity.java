package com.first.myapplication.mht;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.first.myapplication.mht.actions.BottomSheetAction;
import com.first.myapplication.mht.widgets.JarvisMenuBottomSheet;
import com.first.myapplication.mht.widgets.ProfilePopupDialog;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class themeActivity extends AppCompatActivity implements View.OnClickListener, JarvisMenuBottomSheet.ActionListener {

    Button timeManagement, anxiety, internet;
    TextView tvGreetingMessage;
    ExtendedFloatingActionButton menuButton;

    GoogleSignInAccount googleSignInAccount;

    private int hourOfTheDay;

    private ProfilePopupDialog profilePopupDialog;
    private JarvisMenuBottomSheet jarvisMenuBottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        timeManagement = findViewById(R.id.timeManagement);
        anxiety = findViewById(R.id.anxiety);
        internet = findViewById(R.id.internet);
        tvGreetingMessage = findViewById(R.id.tvGreetingMessage);
        menuButton = findViewById(R.id.btnMenu);

        profilePopupDialog = new ProfilePopupDialog();
        jarvisMenuBottomSheet = new JarvisMenuBottomSheet();
        jarvisMenuBottomSheet.addOnActionClickListener(this);

        Date calendarDate = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        String formattedDate = dateFormat.format(calendarDate);
        String formattedDay = dayFormat.format(calendarDate);



        hourOfTheDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        String welcomeMessage = updateGreetingMessage(hourOfTheDay);

        tvGreetingMessage.setText(welcomeMessage + "\nIt's " + formattedDate + ", " + formattedDay);

        googleSignInAccount = GoogleSignIn.getLastSignedInAccount(getApplicationContext());

        Animation animation = AnimationUtils.loadAnimation(themeActivity.this, R.anim.fadein);
        internet.setAnimation(animation);
        timeManagement.setAnimation(animation);
        anxiety.setAnimation(animation);

        internet.setOnClickListener((View.OnClickListener) this);
        timeManagement.setOnClickListener((View.OnClickListener) this);
        anxiety.setOnClickListener((View.OnClickListener) this);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenuBottomSheet();
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
            greetingMessage = "Good Morning";
        else if (hour >= 12 && hour < 16)
            greetingMessage = "Good Afternoon";
        else if(hour>= 16 && hour<21)
            greetingMessage = "Good Evening";
        else
            greetingMessage = "It's Night Time";

        return greetingMessage;
    }

    public void openProfileDialog() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        profilePopupDialog.show(
                fragmentTransaction,
                getResources().getString(R.string.label_profile_popup_dialog)
        );
    }

    private void openMenuBottomSheet() {
        jarvisMenuBottomSheet.show(getSupportFragmentManager(), getString(R.string.label_bottom_sheet_tag));
    }

    @Override
    public void onActionListener(@NotNull BottomSheetAction action) {
        Intent actionIntent;
        switch (action) {
            case OPEN_EXERCISES:
                actionIntent = new Intent(themeActivity.this, ExercisesActivity.class);
                startActivity(actionIntent);
                break;
            case OPEN_SCALE:
                actionIntent = new Intent(themeActivity.this, ScaleDisplayActivity.class);
                startActivity(actionIntent);
                break;
            case OPEN_PROFILE:
                openProfileDialog();
                break;
            case OPEN_ABOUT_US:
                actionIntent = new Intent(themeActivity.this, AboutUsActivity.class);
                startActivity(actionIntent);
                break;
            case OPEN_SIGN_OUT:
                logoutOrCancel();
                break;
            case OPEN_COVID_STATS:
                actionIntent = new Intent(themeActivity.this, Covid.class);
                startActivity(actionIntent);
                break;
        }
    }
}


