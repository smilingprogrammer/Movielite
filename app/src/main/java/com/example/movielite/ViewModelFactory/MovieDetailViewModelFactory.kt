package com.example.movielite.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movielite.network.repository.MovieDetailRepository
import com.example.movielite.viewmodel.DetailViewModel

class MovieDetailViewModelFactory(private val movieDetailRepository: MovieDetailRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(movieDetailRepository) as T
        } else {
            throw IllegalArgumentException("UNKNOWN CLASS")
        }
    }

}