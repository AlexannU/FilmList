package com.example.filmlist.data.remote

import com.example.filmlist.data.remote.responses.FilmResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestApi {
    @GET("svc/movies/v2/reviews/all.json?api-key=JXtwRj3ydwaP11I2yHnGrFRjx1GgtiLH")
    suspend fun getAllFilms(@Query("api-key") apiKey:String,@Query("offset") offset:Int) : FilmResponse
}