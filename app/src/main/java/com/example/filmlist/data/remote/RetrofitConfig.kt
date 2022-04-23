package com.example.filmlist.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    fun createApi():RequestApi{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nytimes.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(RequestApi::class.java)
    }
}