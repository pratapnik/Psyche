package com.first.myapplication.mht.fragments.activities

import android.os.Bundle
import android.os.CountDownTimer
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.first.myapplication.mht.R

class MeditationFragment : Fragment() {

    lateinit var tvTimer: TextView
    lateinit var startButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_meditation, container, false)

        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        tvTimer = view.findViewById(R.id.tvCountDownTimer)
        startButton = view.findViewById(R.id.btnStartExercise)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startButton.setOnClickListener {
            startButton.visibility = View.GONE
            val timer = object : CountDownTimer(60000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    tvTimer.textSize = 32F
                    tvTimer.text = (millisUntilFinished / 1000).toString().plus(" ").plus("seconds")
                }

                override fun onFinish() {
                    startButton.visibility = View.VISIBLE
                    startButton.text = resources.getString(R.string.label_start_again_button_title)
                    tvTimer.textSize = 24F
                    tvTimer.text = resources.getString(R.string.label_meditation_completed_message)
                }
            }
            timer.start()
        }
    }
}
