package com.first.myapplication.mht

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_covid.*


class Covid : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid)



        wvCovidWebsite.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(wvCovidWebsite, url)
                pbCovid19.visibility = View.GONE
            }

            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                Toast.makeText(applicationContext, "Oh no! $description", Toast.LENGTH_SHORT).show()
            }
        }

        wvCovidWebsite.settings.javaScriptEnabled = true
        wvCovidWebsite.settings.domStorageEnabled = true
        wvCovidWebsite.overScrollMode = WebView.OVER_SCROLL_IF_CONTENT_SCROLLS
        wvCovidWebsite.loadUrl("https://www.covid19india.org/")

    }

}
