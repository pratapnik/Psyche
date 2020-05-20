package com.first.myapplication.mht.data

import android.util.Log
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BoredApiService constructor(){



    fun getAge(name: String): Single<BoredApiDataModel> {
        val BASE_URL = "http://www.boredapi.com/api/activity/"
        val api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(BoredApi::class.java)
        Log.d("nikhil", BASE_URL)
        return api.getActivities(BASE_URL)
    }
}