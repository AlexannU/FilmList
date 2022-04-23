package com.example.filmlist.data.remote.responses

import com.google.gson.annotations.SerializedName

data class Film(
    @SerializedName("display_title")
    val displayTitle:String,
    @SerializedName("Claire Shaffer")
    val director:String,
    @SerializedName("summary_short")
    val description:String,
    @SerializedName("multimedia")
    val multimedia:Multimedia
    )
