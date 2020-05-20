package com.first.myapplication.mht.data

import android.util.Log
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BoredApiService constructor(){



    fun getActivity(): Single<BoredApiDataModel> {
        val BASE_URL = "https://official-joke-api.appspot.com"
        val api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(BoredApi::class.java)
        Log.d("nikhil", BASE_URL)

        return api.getActivities()
    }
}