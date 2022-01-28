package com.example.movielite.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movielite.network.repository.TopRatedRepository
import com.example.movielite.viewmodel.TopRatedViewModel

class TopRatedViewModelFactory(private val topRatedRepository: TopRatedRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TopRatedViewModel::class.java)){
            return TopRatedViewModel(topRatedRepository) as T
        } else {
            throw IllegalArgumentException("UNKNOWN CLASS")
        }
    }


}