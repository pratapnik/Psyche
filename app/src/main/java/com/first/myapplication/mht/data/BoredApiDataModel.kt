package com.first.myapplication.mht.data

import com.google.gson.annotations.SerializedName

data class BoredApiDataModel(
        @SerializedName("activity")
        val activityName: String?,
        @SerializedName("accessibility")
        val accessibility: Double?,
        @SerializedName("type")
        val activityType: String?,
        @SerializedName("participants")
        val numberOfParticipants: Long?,
        @SerializedName("price")
        val activityPrice: Double?,
        @SerializedName("link")
        val activityRefLink: String?,
        @SerializedName("key")
        val activityKey: String?
)