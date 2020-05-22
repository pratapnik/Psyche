package com.first.myapplication.mht.widgets

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
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
    lateinit var jokeShareButton: Button
    lateinit var jokeCloseImageView: ImageView

    fun setJokes(jokes: JokesFormatClass) {
        this.jokesFormatClass = jokes
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var layoutInflater: LayoutInflater? = activity?.layoutInflater
        val view = layoutInflater?.inflate(R.layout.jarvis_joke_popup_dialog, null)

        jokeSetupTextView = view?.findViewById(R.id.tvJokeSetup)!!
        jokePunchlineTextView = view.findViewById(R.id.tvJokeDescription)
        jokeCopyButton = view.findViewById(R.id.btnCopyJoke)
        jokeShareButton = view.findViewById(R.id.btnShareJoke)
        jokeCloseImageView = view.findViewById(R.id.ivJokeClose)

        jokePopupDialog = AlertDialog.Builder(activity)
        jokePopupDialog.setView(view)

        jokeSetupTextView.text = jokesFormatClass.jokeSetup
        jokePunchlineTextView.text = jokesFormatClass.jokePunchline

        val fullJokeText = jokesFormatClass.jokeSetup.plus("\n").plus(jokesFormatClass.jokePunchline)

        jokeShareButton.setOnClickListener {
            actionListener?.onShareJokeListener(fullJokeText)
        }

        jokeCloseImageView.setOnClickListener {
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
        fun onShareJokeListener(jokeText: String)
    }

}