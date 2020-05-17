package com.first.myapplication.mht.data

import android.util.Log
import io.reactivex.Single
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NameAgeApiService constructor(){



    fun getAge(name: String): Single<NamesAgeDataModel> {
        val BASE_URL = "https://api.agify.io/?name=".plus(name)
        val api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(NameAgeApi::class.java)
        Log.d("nikhil", BASE_URL)
        return api.getNamesAge(BASE_URL)
    }
}