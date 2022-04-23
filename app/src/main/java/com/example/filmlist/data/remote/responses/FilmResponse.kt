package com.example.filmlist.data.remote.responses

import com.google.gson.annotations.SerializedName

data class FilmResponse(
    @SerializedName("has_more")
    val hasMore:Boolean,
    @SerializedName("num_results")
    val numResults:Int,
    @SerializedName("results")
    val results:List<Film>
)
