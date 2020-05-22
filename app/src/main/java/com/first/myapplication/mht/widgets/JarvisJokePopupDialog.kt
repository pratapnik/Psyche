package com.first.myapplication.mht.widgets

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.first.myapplication.mht.R
import com.first.myapplication.mht.data.JokesFormatClass

class JarvisJokePopupDialog : DialogFragment() {

    private lateinit var jokePopupDialog: AlertDialog.Builder
    lateinit var jokesFormatClass: JokesFormatClass
    var actionListener: ActionListener ?=null

    lateinit var jokeSetupTextView: TextView
    lateinit var jokePunchlineTextView: TextView
    lateinit var jokeCopyButton: Button
    lateinit var jokeOkButton: Button

    fun setJokes(jokes: JokesFormatClass) {
        this.jokesFormatClass = jokes
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var layoutInflater: LayoutInflater? = activity?.layoutInflater
        val view = layoutInflater?.inflate(R.layout.jarvis_joke_popup_dialog, null)

        jokeSetupTextView = view?.findViewById(R.id.tvJokeSetup)!!
        jokePunchlineTextView = view.findViewById(R.id.tvJokeDescription)!!
        jokeCopyButton = view.findViewById(R.id.btnCopyJoke)!!
        jokeOkButton = view.findViewById(R.id.btnOkJoke)!!

        jokePopupDialog = AlertDialog.Builder(activity)
        jokePopupDialog.setView(view)

        jokeSetupTextView.text = jokesFormatClass.jokeSetup
        jokePunchlineTextView.text = jokesFormatClass.jokePunchline

        val fullJokeText = jokesFormatClass.jokeSetup.plus("\n").plus(jokesFormatClass.jokePunchline)

        jokeOkButton.setOnClickListener {
            dismiss()
        }

        jokeCopyButton.setOnClickListener {
            actionListener?.onCopyListener(fullJokeText)
            dismiss()
        }

        return jokePopupDialog.create()
    }

    interface ActionListener{
        fun onCopyListener(jokeText: String)
    }

}