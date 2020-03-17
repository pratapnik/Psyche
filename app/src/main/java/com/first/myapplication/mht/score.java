package com.first.myapplication.mht;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class score extends AppCompatActivity {

    private TextView scoreTV,details;
    private Intent mIntent = new Intent();
    private Intent thIntent = new Intent();
    private int mScore,active;

    Button buttonGoToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreTV = findViewById(R.id.tv_score_data);
        details = findViewById(R.id.tv_details);
        buttonGoToHome = findViewById(R.id.btn_go_to_home);


        mIntent = getIntent();
        thIntent = getIntent();
        mScore = mIntent.getIntExtra("score", 0);
        active = thIntent.getIntExtra("type",1);
        scoreTV.setText("" + mScore);


        buttonGoToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(score.this, themeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });


        if(active==0){
            if(mScore>=8 && mScore<=24){
                details.setText("Good News! Your Score is in the safe zone. You are great at monitoring the amount of time you spend online");
                details.setTextColor(Color.GREEN);
            }
            else{
                details.setText("Alert! Your Score suggests you are addicted to the internet. Start with limiting and monitoring your internet usage");
                details.setTextColor(Color.parseColor("#E74C3C"));
            }
        }
        else if(active==1){
            if(mScore>=18 && mScore<=46){
                details.setText("Good News! Your Score is in the safe zone. Your are great at managing your time");
                details.setTextColor(Color.GREEN);

            }
            else if(mScore>=47 && mScore<=57){
                details.setText("Caution! You are doing okay but you need to focus on improving your time management");
                details.setTextColor(Color.WHITE);
            }
            else{
                details.setText("Alert! Poor time management is a likely reason behind your mental health issues");
                details.setTextColor(Color.parseColor("#E74C3C"));

            }
        }
        else{
            if(mScore>=23 && mScore<=44){
                details.setText("Good News! Your Score is in the safe zone.");
                details.setTextColor(Color.GREEN);

            }
            else if(mScore>=45 && mScore<=81){
                details.setText("Caution! You are doing okay but it will be better to keep your stress and anxiety levels in check");
                details.setTextColor(Color.WHITE);
            }
            else{
                details.setText("Alert! High Anxiety levels is likely reason behind your stress");
                details.setTextColor(Color.parseColor("#E74C3C"));
            }
        }




    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(score.this, themeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
