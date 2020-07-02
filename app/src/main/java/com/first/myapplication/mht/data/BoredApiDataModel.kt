package com.first.myapplication.mht.data

import com.google.gson.annotations.SerializedName

data class BoredApiDataModel(
        @SerializedName("id")
        val jokeId: Long?,
        @SerializedName("type")
        val jokeType: String?,
        @SerializedName("setup")
        val jokeSetup: String?,
        @SerializedName("punchline")
        val jokePunchline: String?
)