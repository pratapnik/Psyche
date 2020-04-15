package com.first.myapplication.mht

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import kotlinx.android.synthetic.main.activity_exercises.*

class ExercisesActivity : AppCompatActivity() {

    lateinit var exerciseBundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises)

        toolbarExercises.setNavigationOnClickListener {
            onBackPressed()
        }

        exerciseBundle = Bundle()

        llMeditation.setOnClickListener {
            exerciseBundle.putString("exType", "Meditation")
            exerciseBundle.putString("fragmentType", "meditation")
            loadExcercise(exerciseBundle)
        }
    }

    fun loadExcercise(exerciseBundle: Bundle){
         val displayExcerciseIntent = Intent(this, DisplayExcercise::class.java)
         displayExcerciseIntent.putExtra("exTypeBundle", exerciseBundle)
         startActivity(displayExcerciseIntent)
    }
}
