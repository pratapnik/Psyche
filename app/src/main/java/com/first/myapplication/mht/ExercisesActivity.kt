package com.first.myapplication.mht

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.first.myapplication.mht.data.BoredApiService
import com.first.myapplication.mht.data.BoredApiDataModel
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_exercises.*

class ExercisesActivity : AppCompatActivity() {

    lateinit var exerciseBundle: Bundle

    private val namesAgeApiService = BoredApiService()

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

        btnGetNameAge.setOnClickListener {
            val result = namesAgeApiService.getActivity()

            result.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<BoredApiDataModel>() {
                        override fun onSuccess(boredApiFromApi: BoredApiDataModel) {
                            Log.d("nikhil", boredApiFromApi.jokeId.toString() )
                            val snackbar = Snackbar.make(it, boredApiFromApi.jokeSetup
                                    .plus(boredApiFromApi.jokePunchline) , Snackbar.LENGTH_LONG)
                            snackbar.show()
                        }

                        override fun onError(e: Throwable) {
                            Log.d("nikhil", e.message)
                            e.printStackTrace()
                        }

                    })

        }
    }

    fun loadExcercise(exerciseBundle: Bundle) {
        val displayExcerciseIntent = Intent(this, DisplayExcercise::class.java)
        displayExcerciseIntent.putExtra("exTypeBundle", exerciseBundle)
        startActivity(displayExcerciseIntent)
    }
}
