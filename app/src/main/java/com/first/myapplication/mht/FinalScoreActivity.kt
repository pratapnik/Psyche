package com.first.myapplication.mht

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profile.*
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

        btnHome.setOnClickListener {
            val themeActivityIntent = Intent(this, themeActivity::class.java)
            startActivity(themeActivityIntent)
        }

        btnCheckScale.setOnClickListener {
            val scaleIntent = Intent(this, ScaleDisplayActivity::class.java)
            startActivity(scaleIntent)
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
                theme = "Internet Addiction"
            }
            1 -> if (score >= 18 && score <= 46) {
                tvResultDescription.text = resources.getText(R.string.label_time_management_good_score_description)
                ivScoreDescriptionIcon.setImageResource(R.drawable.ic_like)
                theme = "Time Management"
            } else if (score >= 47 && score <= 57) {
                tvResultDescription.text = resources.getText(R.string.label_time_management_average_score_description)
                theme = "Time Management"
            } else {
                tvResultDescription.text = resources.getText(R.string.label_time_management_bad_score_description)
                ivScoreDescriptionIcon.setImageResource(R.drawable.ic_dislike)
                theme = "Time Management"
            }
            2 -> if (score >= 23 && score <= 44) {
                tvResultDescription.text = resources.getText(R.string.label_anxiety_good_score_description)
                ivScoreDescriptionIcon.setImageResource(R.drawable.ic_like)
                theme = "Anxiety"
            } else if (score >= 45 && score <= 81) {
                tvResultDescription.text = resources.getText(R.string.label_anxiety_average_score_description)
                theme = "Anxiety"
            } else {
                tvResultDescription.text = resources.getText(R.string.label_anxiety_bad_score_description)
                ivScoreDescriptionIcon.setImageResource(R.drawable.ic_dislike)
                theme = "Anxiety"
            }
            else -> Toast.makeText(this, "Here's Your Previous Score", Toast.LENGTH_LONG).show()
        }

        return theme
    }
}