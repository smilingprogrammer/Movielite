package com.example.movielite.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movielite.network.repository.MovieRepository
import com.example.movielite.viewmodel.MainViewModel

class MainViewModelFactory(private val movieRepository: MovieRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(movieRepository) as T
        } else {
            throw IllegalArgumentException("UNKNOWN")
        }
    }
}