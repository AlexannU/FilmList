package com.example.filmlist.data.remote.responses

import com.google.gson.annotations.SerializedName

data class Multimedia(
    @SerializedName("type")
    val type:String,
    @SerializedName("src")
    val src:String
)
