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
import androidx.navigation.findNavController

import com.first.myapplication.mht.R
import kotlinx.android.synthetic.main.fragment_india_covid.*

class IndiaCovidFragment : Fragment() {
    
    lateinit var webViewIndia: WebView
    lateinit var progressBarIndia: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_india_covid, container, false)
        
        webViewIndia = view.findViewById(R.id.wvCovidWebsite)
        progressBarIndia = view.findViewById(R.id.pbCovid19)
        
        webViewIndia.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(webViewIndia, url)
                progressBarIndia.visibility = View.GONE
            }

            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                Toast.makeText(context, "Oh no! $description", Toast.LENGTH_SHORT).show()
            }
        }

        webViewIndia.settings.javaScriptEnabled = true
        webViewIndia.settings.domStorageEnabled = true
        webViewIndia.overScrollMode = WebView.OVER_SCROLL_IF_CONTENT_SCROLLS
        webViewIndia.loadUrl("https://www.covid19india.org/")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnWorldStats.setOnClickListener {
            view.findNavController().navigate(R.id.action_indiaCovidFragment_to_worldCovidFragmet)
        }
    }

}
