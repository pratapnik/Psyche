package com.first.myapplication.mht.fragments.activities

import android.app.ProgressDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.first.myapplication.mht.R
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
import kotlinx.android.synthetic.main.fragment_activities_home.*

class ActivitiesHomeFragment : Fragment(), JarvisJokePopupDialog.ActionListener {

    lateinit var jokePopupDialog: JarvisJokePopupDialog
    lateinit var progressDialog: ProgressDialog
    private val namesAgeApiService = BoredApiService()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_activities_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        llMeditation.setOnClickListener {
            view.findNavController().navigate(R.id.action_activitiesHomeFragment_to_meditationFragment)
        }

        progressDialog = ProgressDialog(context)
        jokePopupDialog = JarvisJokePopupDialog()


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

    private fun copyText(text: String) {
        val clipboard = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
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
        val fragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        jokePopupDialog.actionListener = this
        jokePopupDialog.setJokes(jokesFormatClass)
        jokePopupDialog.show(
                fragmentTransaction,
                resources.getString(R.string.label_joke_popup_dialog)
        )
        hideProgressDialogWithTitle(progressDialog)
    }

    override fun onCopyListener(jokeText: String) {
        copyText(jokeText)
    }

    override fun onShareJokeListener(jokeText: String) {
        sendJoke(jokeText)
    }

}

