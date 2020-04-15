package com.first.myapplication.mht

import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_meditation.*


/**
 * A simple [Fragment] subclass.
 */
class MeditationFragment : Fragment() {

    lateinit var tvTimer: TextView
    lateinit var startButton : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_meditation, container, false)

        tvTimer = view.findViewById(R.id.tvCountDownTimer)
        startButton = view.findViewById(R.id.btnStartExercise)

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startButton.setOnClickListener {
            startButton.isClickable = false
            startButton.alpha = 0.5F
            val timer = object : CountDownTimer(60000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    tvTimer.text = "Seconds Left: ".plus((millisUntilFinished / 1000).toString())
                }

                override fun onFinish() {
                    startButton.isClickable = true
                    startButton.alpha = 1F
                    tvTimer.text = "DONE"
                }
            }
            timer.start()
        }
    }

}
