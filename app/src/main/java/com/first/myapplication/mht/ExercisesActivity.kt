package com.first.myapplication.mht

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_exercises.*

class ExercisesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises)

        ivExerciseBack.setOnClickListener {
            onBackPressed()
        }
    }
}
