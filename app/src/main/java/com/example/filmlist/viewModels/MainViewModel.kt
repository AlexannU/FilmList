package com.example.filmlist.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.filmlist.data.remote.FilmsPageSource
import com.example.filmlist.data.remote.MainRepository
import com.example.filmlist.data.remote.responses.Film
import com.example.filmlist.data.remote.responses.FilmResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private val _isLoad = MutableStateFlow(false)
    val isLoading:StateFlow<Boolean> get() = _isLoad

    val filmsFlow = Pager(
        PagingConfig(pageSize = 20, initialLoadSize = 20)
    ){
        FilmsPageSource(mainRepository)
    }.flow.cachedIn(viewModelScope)


    fun confirmLoading(){
        _isLoad.value = true
    }


}