package com.first.myapplication.mht

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.first.myapplication.mht.data.NameAgeApiService
import com.first.myapplication.mht.data.NamesAgeDataModel
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_exercises.*

class ExercisesActivity : AppCompatActivity() {

    lateinit var exerciseBundle: Bundle

    private val namesAgeApiService = NameAgeApiService()

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
            val enteredName = etName.text.toString()

            val result = namesAgeApiService.getAge(enteredName)

            result.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<NamesAgeDataModel>() {
                        override fun onSuccess(namesAgeFromApi: NamesAgeDataModel) {
                            Log.d("nikhil", namesAgeFromApi.count.toString())
                            val snackbar = Snackbar.make(it, namesAgeFromApi.name.plus(",").plus(namesAgeFromApi.age).plus(",").plus(namesAgeFromApi.count), Snackbar.LENGTH_LONG)
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
