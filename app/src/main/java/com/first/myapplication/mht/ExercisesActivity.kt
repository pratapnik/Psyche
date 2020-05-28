package com.first.myapplication.mht

import android.app.ProgressDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.first.myapplication.mht.data.BoredApiDataModel
import com.first.myapplication.mht.data.BoredApiService
import com.first.myapplication.mht.data.JarvisJokesFormatClass
import com.first.myapplication.mht.utils.hideProgressDialogWithTitle
import com.first.myapplication.mht.utils.showProgressDialogWithTitle
import com.first.myapplication.mht.utils.showSnackBar
import com.first.myapplication.mht.widgets.JarvisJokePopupDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_exercises.*

class ExercisesActivity : AppCompatActivity(), JarvisJokePopupDialog.ActionListener {

    lateinit var exerciseBundle: Bundle
    lateinit var jokePopupDialog: JarvisJokePopupDialog
    lateinit var progressDialog: ProgressDialog

    private val namesAgeApiService = BoredApiService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises)

        toolbarExercises.setNavigationOnClickListener {
            onBackPressed()
        }

        progressDialog = ProgressDialog(this)
        exerciseBundle = Bundle()
        jokePopupDialog = JarvisJokePopupDialog()

        llMeditation.setOnClickListener {
            exerciseBundle.putString("exType", "Meditation")
            exerciseBundle.putString("fragmentType", "meditation")
            loadExcercise(exerciseBundle)
        }

        btnRandomJoke.setOnClickListener {
            showProgressDialogWithTitle("Getting Joke", progressDialog)
            val result = namesAgeApiService.getActivity()
            result.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<BoredApiDataModel>() {
                        override fun onSuccess(boredApiFromApi: BoredApiDataModel) {
                            val jokesFormatClass = JarvisJokesFormatClass(boredApiFromApi.jokeSetup, boredApiFromApi.jokePunchline)
                            openJokeDialog(jokesFormatClass)
                        }

                        override fun onError(e: Throwable) {
                            clExercise.showSnackBar(getString(R.string.label_error_message))
                            e.printStackTrace()
                        }
                    })

        }
    }

    private fun loadExcercise(exerciseBundle: Bundle) {
        val displayExerciseIntent = Intent(this, DisplayExerciseActivity::class.java)
        displayExerciseIntent.putExtra("exTypeBundle", exerciseBundle)
        startActivity(displayExerciseIntent)
    }

    private fun copyText(text: String) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("joke", text)
        clipboard.setPrimaryClip(clip)
        clExercise.showSnackBar("Joke is copied to clipboard")
    }

    private fun sendJoke(text: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.putExtra(Intent.EXTRA_TEXT, text)
        shareIntent.type = "text/plain"
        val sendIntent = Intent.createChooser(shareIntent, "Send Joke")
        startActivity(sendIntent)
    }

    fun openJokeDialog(jokesFormatClass: JarvisJokesFormatClass) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        jokePopupDialog.setJokes(jokesFormatClass)
        jokePopupDialog.show(
                fragmentTransaction,
                resources.getString(R.string.label_joke_popup_dialog)
        )
        jokePopupDialog.actionListener = this
        hideProgressDialogWithTitle(progressDialog)
    }

    override fun onCopyListener(jokeText: String) {
        copyText(jokeText)
    }

    override fun onShareJokeListener(jokeText: String) {
        sendJoke(jokeText)
    }
}
