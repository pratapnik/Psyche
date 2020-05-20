package com.first.myapplication.mht.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface BoredApi {
    @GET
    fun getActivities(@Url url: String): Single<BoredApiDataModel>
}