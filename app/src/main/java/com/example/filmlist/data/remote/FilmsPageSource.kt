package com.example.filmlist.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.filmlist.BuildConfig
import com.example.filmlist.data.remote.responses.Film
import retrofit2.HttpException

class FilmsPageSource(private val mainRepository: MainRepository) : PagingSource<Int,Film>() {
    
    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        val page = params.key ?: 0
        val pageSize = params.loadSize
        return try {
            val response = mainRepository.getFilmResponse(page)
            val films = response.results
            LoadResult.Page(
                data = films,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (films.size < pageSize) null else page + 1
            )
        }
        catch (e:Exception){
            LoadResult.Error(e)
        }

    }
}