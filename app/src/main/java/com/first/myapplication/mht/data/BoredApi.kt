package com.first.myapplication.mht.data

import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface BoredApi {

    @GET("jokes/random")
    fun getActivities(): Single<BoredApiDataModel>
}