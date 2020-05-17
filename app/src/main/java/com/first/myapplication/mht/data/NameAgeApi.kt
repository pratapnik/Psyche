package com.first.myapplication.mht.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface NameAgeApi {
    @GET
    fun getNamesAge(@Url url: String): Single<NamesAgeDataModel>
}