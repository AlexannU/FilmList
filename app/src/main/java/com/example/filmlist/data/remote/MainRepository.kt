package com.example.filmlist.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.filmlist.BuildConfig
import com.example.filmlist.data.remote.responses.Film
import com.example.filmlist.data.remote.responses.FilmResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {
suspend fun getAllFilms(page:Int):Result<FilmResponse>
suspend fun getFilmResponse(page:Int):FilmResponse
}
class MainRepositoryImpl(private val requestApi:RequestApi) : MainRepository{
    override suspend fun getAllFilms(page:Int): Result<FilmResponse> {
        return try {
            Result.success(
                requestApi.getAllFilms(BuildConfig.API_KEY,page * 20)
            )
        } catch (e:Exception){
            Result.failure(e)
        }
    }

    override suspend fun getFilmResponse(page: Int): FilmResponse {
        return requestApi.getAllFilms(BuildConfig.API_KEY,page * 20)
    }


}