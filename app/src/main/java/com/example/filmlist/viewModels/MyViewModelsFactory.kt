package com.example.filmlist.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.filmlist.data.remote.MainRepository
import java.security.Provider

class MyViewModelsFactory(private val mainRepository:MainRepository) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return  MainViewModel(mainRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}