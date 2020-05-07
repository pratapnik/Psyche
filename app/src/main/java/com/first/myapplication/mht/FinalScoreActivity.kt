package com.first.myapplication.mht

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_score.*

class FinalScoreActivity : AppCompatActivity() {

    lateinit var scoreIntent: Intent
    lateinit var typeIntent: Intent
    var scoreValue: Int = 0
    var themeType: Int = 0
    lateinit var themeDescription: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        scoreValue = getYourScoreValue()
        themeType = getYourThemeType()

        tvScoreValue.text = scoreValue.toString()

        themeDescription = setTextAndColor(scoreValue, themeType)

        toolbarScore.setNavigationOnClickListener {
            onBackPressed()
        }

        btnHome.setOnClickListener {
            val themeActivityIntent = Intent(this, themeActivity::class.java)
            startActivity(themeActivityIntent)
        }

        btnExercises.setOnClickListener {
            val excerciseIntent = Intent(this, ExercisesActivity::class.java)
            startActivity(excerciseIntent)
        }

    }


    fun getYourScoreValue(): Int {
        scoreIntent = intent
        return scoreIntent.getIntExtra("score", 0)
    }

    fun getYourThemeType(): Int {
        typeIntent = intent
        return typeIntent.getIntExtra("type", 1)
    }

    fun setTextAndColor(score: Int, themeType: Int): String {
        var theme = "No theme"
        when (themeType) {
            0 -> if (score >= 8 && score <= 24) {
                tvResultDescription.text = resources.getText(R.string.label_internet_addiction_good_score_description)
                ivScoreDescriptionIcon.setImageResource(R.drawable.ic_like)
                theme = "Internet Addiction"
            } else {
                tvResultDescription.text = resources.getText(R.string.label_internet_addiction_bad_score_description)
                ivScoreDescriptionIcon.setImageResource(R.drawable.ic_dislike)
                btnExercises.visibility = View.VISIBLE
                theme = "Internet Addiction"
            }
            1 -> if (score >= 18 && score <= 46) {
                tvResultDescription.text = resources.getText(R.string.label_time_management_good_score_description)
                ivScoreDescriptionIcon.setImageResource(R.drawable.ic_like)
                theme = "Time Management"
            } else if (score >= 47 && score <= 57) {
                tvResultDescription.text = resources.getText(R.string.label_time_management_average_score_description)
                theme = "Time Management"
                btnExercises.visibility = View.VISIBLE
            } else {
                tvResultDescription.text = resources.getText(R.string.label_time_management_bad_score_description)
                ivScoreDescriptionIcon.setImageResource(R.drawable.ic_dislike)
                theme = "Time Management"
                btnExercises.visibility = View.VISIBLE
            }
            2 -> if (score >= 23 && score <= 44) {
                tvResultDescription.text = resources.getText(R.string.label_anxiety_good_score_description)
                ivScoreDescriptionIcon.setImageResource(R.drawable.ic_like)
                theme = "Anxiety"
            } else if (score >= 45 && score <= 81) {
                tvResultDescription.text = resources.getText(R.string.label_anxiety_average_score_description)
                theme = "Anxiety"
                btnExercises.visibility = View.VISIBLE
            } else {
                tvResultDescription.text = resources.getText(R.string.label_anxiety_bad_score_description)
                ivScoreDescriptionIcon.setImageResource(R.drawable.ic_dislike)
                theme = "Anxiety"
                btnExercises.visibility = View.VISIBLE
            }
            else -> Toast.makeText(this, "Here's Your Previous Score", Toast.LENGTH_LONG).show()
        }

        return theme
    }
}