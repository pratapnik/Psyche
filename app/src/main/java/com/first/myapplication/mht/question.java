package com.first.myapplication.mht;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class question extends AppCompatActivity {

    private TextView mQuestion;
    private Button mChoice1, mChoice2, mChoice3, mChoice4, mChoice5, mNext;
    private RadioGroup mOptionsRG;
    private Firebase mQuestionRef;
    private int mQuestionNo = 1;
    private int mScore = 0;
    private Intent mIntent = new Intent();
    private int type;
    private boolean reverse = false;

    loginActivity loginActivity = new loginActivity();

    ProgressDialog progressDialog;

    Toolbar toolbarQuestion;

    Animation animationLeave, animationLeftToRight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Firebase.setAndroidContext(this);

        mQuestion = findViewById(R.id.tv_question);
        mChoice1 = findViewById(R.id.choice1);
        mChoice2 = findViewById(R.id.choice2);
        mChoice3 = findViewById(R.id.choice3);
        mChoice4 = findViewById(R.id.choice4);
        mChoice5 = findViewById(R.id.choice5);
        mOptionsRG = findViewById(R.id.rg_five_options);
        mNext = findViewById(R.id.btn_next);
        toolbarQuestion = findViewById(R.id.toolbar_question_activity);

        progressDialog = new ProgressDialog(this);
        loginActivity.showProgressDialogWithTitle("Loading the questions..", progressDialog);

        animationLeave = AnimationUtils.loadAnimation(question.this, R.anim.jarvis_leave_left_to_right);
        animationLeftToRight = AnimationUtils.loadAnimation(question.this, R.anim.lefttoright);

        toolbarQuestion.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextButton();
            }
        });

        mIntent = getIntent();
        type = mIntent.getIntExtra("type", 0);

        updateQuestion();
    }

    private void updateQuestion() {
        mQuestionRef = new Firebase("https://mental-health-tracker-bb023.firebaseio.com/" +
                type + "/questions/" + (mQuestionNo - 1) + "/question");

        mQuestionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String question = dataSnapshot.getValue(String.class);
                mQuestion.setText(question);
                loginActivity.hideProgressDialogWithTitle(progressDialog);
                mOptionsRG.setVisibility(View.VISIBLE);
                mNext.setVisibility(View.VISIBLE);
                setLeftToRightAnimation();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        reverse = (type == 1 && (mQuestionNo == 8 || mQuestionNo == 10 || mQuestionNo == 12 ||
                mQuestionNo == 14)) || (type == 2 && (mQuestionNo == 14 || mQuestionNo == 16 ||
                mQuestionNo == 18));
    }

    private void nextButton() {

        switch (mOptionsRG.getCheckedRadioButtonId()) {
            case R.id.choice1:
                if (reverse) {
                    mScore = mScore + 5;
                } else {
                    mScore = mScore + 1;
                }
                setLeaveAnimation();
                break;
            case R.id.choice2:
                if (reverse) {
                    mScore = mScore + 4;
                } else {
                    mScore = mScore + 2;
                }
                setLeaveAnimation();
                break;
            case R.id.choice3:
                mScore = mScore + 3;
                setLeaveAnimation();
                break;
            case R.id.choice4:
                if (reverse) {
                    mScore = mScore + 2;
                } else {
                    mScore = mScore + 4;
                }
                setLeaveAnimation();
                break;
            case R.id.choice5:
                if (reverse) {
                    mScore = mScore + 1;
                } else {
                    mScore = mScore + 5;
                }
                setLeaveAnimation();
                break;
            case -1:
                Toast.makeText(this, "MARK ATLEAST ONE CHOICE", Toast.LENGTH_LONG).show();
                break;
        }

        Intent i = new Intent(question.this, FinalScoreActivity.class);
        if (type == 0 && mOptionsRG.getCheckedRadioButtonId() != -1) {
            if (mQuestionNo == 8) {
                i.putExtra("score", mScore);
                i.putExtra("type",type);
                startActivity(i);
            } else {
                if (mQuestionNo == 7) {
                    mNext.setText("SUBMIT");
                    mNext.setTextColor(getResources().getColor(R.color.color_white));
                    mNext.setBackgroundColor(getResources().getColor(R.color.color_app_theme_accent));
                }
                mQuestionNo++;
                mOptionsRG.clearCheck();
                updateQuestion();
            }
        } else if (type == 1 && mOptionsRG.getCheckedRadioButtonId() != -1) {
            if (mQuestionNo == 18) {
                i.putExtra("score", mScore);
                i.putExtra("type",type);
                startActivity(i);
            } else {
                if (mQuestionNo == 17) {
                    mNext.setText("SUBMIT");
                    mNext.setTextColor(getResources().getColor(R.color.color_white));
                    mNext.setBackgroundColor(getResources().getColor(R.color.color_app_theme_accent));
                }
                mQuestionNo++;
                mOptionsRG.clearCheck();
                updateQuestion();
            }
        } else if (type == 2 && mOptionsRG.getCheckedRadioButtonId() != -1) {
            if (mQuestionNo == 23) {
                i.putExtra("score", mScore);
                i.putExtra("type",type);
                startActivity(i);
            } else {
                if (mQuestionNo == 22) {
                    mNext.setText("SUBMIT");
                    mNext.setTextColor(getResources().getColor(R.color.color_white));
                    mNext.setBackgroundColor(getResources().getColor(R.color.color_app_theme_accent));
                }
                mQuestionNo++;
                mOptionsRG.clearCheck();
                updateQuestion();
            }
        }
    }

    private void setLeaveAnimation(){
        mQuestion.startAnimation(animationLeave);
        mOptionsRG.startAnimation(animationLeave);
        mNext.startAnimation(animationLeave);
    }

    private void setLeftToRightAnimation(){
        mQuestion.startAnimation(animationLeftToRight);
        mOptionsRG.startAnimation(animationLeftToRight);
        mNext.startAnimation(animationLeftToRight);
    }
}


