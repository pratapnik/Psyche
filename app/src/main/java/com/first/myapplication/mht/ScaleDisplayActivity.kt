package com.first.myapplication.mht

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_scale.*

class ScaleDisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scale)

        toolbar_scale.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}