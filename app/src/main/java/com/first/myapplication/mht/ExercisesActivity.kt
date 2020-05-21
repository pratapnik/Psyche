package com.first.myapplication.mht

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.first.myapplication.mht.data.BoredApiDataModel
import com.first.myapplication.mht.data.BoredApiService
import com.first.myapplication.mht.data.JokesFormatClass
import com.first.myapplication.mht.utils.hideProgressDialogWithTitle
import com.first.myapplication.mht.utils.showProgressDialogWithTitle
import com.first.myapplication.mht.widgets.JarvisJokePopupDialog
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_exercises.*

class ExercisesActivity : AppCompatActivity() {

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
            var snackBar:Snackbar
            result.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<BoredApiDataModel>() {
                        override fun onSuccess(boredApiFromApi: BoredApiDataModel) {
                            val jokesFormatClass = JokesFormatClass(boredApiFromApi.jokeSetup, boredApiFromApi.jokePunchline)
                            openJokeDialog(jokesFormatClass)
                        }

                        override fun onError(e: Throwable) {
                            snackBar = Snackbar.make(it, "There is some problem", Snackbar.LENGTH_LONG)
                            snackBar.show()
                            e.printStackTrace()
                        }
                    })

        }
    }

    private fun loadExcercise(exerciseBundle: Bundle) {
        val displayExerciseIntent = Intent(this, DisplayExcercise::class.java)
        displayExerciseIntent.putExtra("exTypeBundle", exerciseBundle)
        startActivity(displayExerciseIntent)
    }

    fun openJokeDialog(jokesFormatClass: JokesFormatClass){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        jokePopupDialog.setJokes(jokesFormatClass)
        jokePopupDialog.show(
                fragmentTransaction,
                resources.getString(R.string.label_joke_popup_dialog)
        )

        hideProgressDialogWithTitle(progressDialog)
    }
}
