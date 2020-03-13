package com.first.myapplication.mht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class question extends AppCompatActivity {

    private TextView mQuestionNoText, mQuestion;
    private Button mChoice1, mChoice2, mChoice3, mChoice4, mChoice5, mNext;
    private RadioGroup mOptionsRG;
    private Firebase mQuestionRef;
    private int mQuestionNo = 1;
    private int mScore = 0;
    private Intent mIntent = new Intent();
    private int type;
    private boolean reverse = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Firebase.setAndroidContext(this);

        mQuestionNoText = findViewById(R.id.question_number);
        mQuestion = findViewById(R.id.question);
        mChoice1 = findViewById(R.id.choice1);
        mChoice2 = findViewById(R.id.choice2);
        mChoice3 = findViewById(R.id.choice3);
        mChoice4 = findViewById(R.id.choice4);
        mChoice5 = findViewById(R.id.choice5);
        mOptionsRG = findViewById(R.id.options_rg);
        mNext = findViewById(R.id.next);

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
        mQuestionRef = new Firebase("https://mental-health-tracker-bb023.firebaseio.com/" + type + "/questions/" + (mQuestionNo - 1) + "/question");
        mQuestionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String question = dataSnapshot.getValue(String.class);
                mQuestion.setText(question);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        reverse = (type == 1 && (mQuestionNo == 8 || mQuestionNo == 10 || mQuestionNo == 12 || mQuestionNo == 14)) || (type == 2 && (mQuestionNo == 14 || mQuestionNo == 16 || mQuestionNo == 18));
    }

    private void nextButton() {

        switch (mOptionsRG.getCheckedRadioButtonId()) {
            case R.id.choice1:
                if (reverse) {
                    mScore = mScore + 5;
                } else {
                    mScore = mScore + 1;
                }
                break;
            case R.id.choice2:
                if (reverse) {
                    mScore = mScore + 4;
                } else {
                    mScore = mScore + 2;
                }
                break;
            case R.id.choice3:
                mScore = mScore + 3;
                break;
            case R.id.choice4:
                if (reverse) {
                    mScore = mScore + 2;
                } else {
                    mScore = mScore + 4;
                }
                break;
            case R.id.choice5:
                if (reverse) {
                    mScore = mScore + 1;
                } else {
                    mScore = mScore + 5;
                }
                break;
            case -1:
                Toast.makeText(this, "Please make a selection", Toast.LENGTH_SHORT).show();
                break;
        }

        Intent i = new Intent(question.this, score.class);
        if (type == 0 && mOptionsRG.getCheckedRadioButtonId() != -1) {
            if (mQuestionNo == 8) {
                i.putExtra("score", mScore);
                i.putExtra("type",type);
                startActivity(i);
            } else {
                if (mQuestionNo == 7) {
                    mNext.setText("SUBMIT");
                }
                mQuestionNo++;
                mOptionsRG.clearCheck();
                updateQuestion();
                mQuestionNoText.setText("" + mQuestionNo);
            }
        } else if (type == 1 && mOptionsRG.getCheckedRadioButtonId() != -1) {
            if (mQuestionNo == 18) {
                i.putExtra("score", mScore);
                i.putExtra("type",type);

                startActivity(i);
            } else {
                if (mQuestionNo == 17) {
                    mNext.setText("SUBMIT");
                }
                mQuestionNo++;
                mOptionsRG.clearCheck();
                updateQuestion();
                mQuestionNoText.setText("" + mQuestionNo);
            }
        } else if (type == 2 && mOptionsRG.getCheckedRadioButtonId() != -1) {
            if (mQuestionNo == 23) {
                i.putExtra("score", mScore);
                i.putExtra("type",type);
                startActivity(i);
            } else {
                if (mQuestionNo == 22) {
                    mNext.setText("SUBMIT");
                }
                mQuestionNo++;
                mOptionsRG.clearCheck();
                updateQuestion();
                mQuestionNoText.setText("" + mQuestionNo);
            }
        }
    }
}


