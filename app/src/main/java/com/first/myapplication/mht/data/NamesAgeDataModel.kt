package com.first.myapplication.mht.data

import com.google.gson.annotations.SerializedName

data class NamesAgeDataModel(
        @SerializedName("name")
        val name: String?,
        @SerializedName("age")
        val age: Long?,
        @SerializedName("count")
        val count: Long?
)