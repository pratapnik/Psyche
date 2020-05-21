package com.first.myapplication.mht.fragments.covid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.first.myapplication.mht.R
import kotlinx.android.synthetic.main.fragment_india_covid.*
import kotlinx.android.synthetic.main.fragment_world_covid_fragmet.*

class WorldCovidFragmet : Fragment() {

    lateinit var webViewWorld: WebView
    lateinit var progressBarWorld: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_world_covid_fragmet, container, false)

        webViewWorld = view.findViewById(R.id.wvCovidWorldWebsite)
        progressBarWorld = view.findViewById(R.id.pbCovid19World)

        webViewWorld.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(webViewWorld, url)
                progressBarWorld.visibility = View.GONE
            }

            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                Toast.makeText(context, "Oh no! $description", Toast.LENGTH_SHORT).show()
            }
        }

        webViewWorld.settings.javaScriptEnabled = true
        webViewWorld.settings.domStorageEnabled = true
        webViewWorld.overScrollMode = WebView.OVER_SCROLL_IF_CONTENT_SCROLLS
        webViewWorld.loadUrl("https://www.worldometers.info/coronavirus/#countries")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnIndiaStats.setOnClickListener {
            view.findNavController().navigate(R.id.action_worldCovidFragmet_to_indiaCovidFragment)
        }
    }

}
