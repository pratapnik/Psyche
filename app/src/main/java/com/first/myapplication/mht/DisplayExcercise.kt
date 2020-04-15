package com.first.myapplication.mht

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_display_excercise.*

class DisplayExcercise : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_excercise)

        val excerciseIntent = intent.getBundleExtra("exTypeBundle")

        excerciseIntent?.getString("exType", "null")?.let { setLayout(it) }

        excerciseIntent?.getString("fragmentType", "null")?.let { loadExerciseFragment(it) }

        toolbarDisplayExercises.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    fun loadExerciseFragment(fragmentType: String) {
        var fragment: Fragment = Fragment()
        if (fragmentType == "meditation")
            fragment = MeditationFragment()

        val fragmentTransaction = supportFragmentManager.beginTransaction().replace(R.id.flExercise, fragment)
        fragmentTransaction.commit()
    }

    fun setLayout(toolbarTitle: String) {
        toolbarDisplayExercises.title = toolbarTitle
    }
}
