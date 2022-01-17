package com.example.movielite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movielite.main.MainViewModel
import com.example.movielite.network.repository.MovieDetailRepository
import com.example.movielite.network.repository.MovieRepository

class MovieDetailViewModelFactory(private val movieDetailRepository: MovieDetailRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailFragmentViewModel::class.java)){
            return MovieDetailFragmentViewModel(movieDetailRepository) as T
        } else {
            throw IllegalArgumentException("UNKNOWN CLASS")
        }
    }

}