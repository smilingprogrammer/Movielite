package com.example.movielite.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.movielite.databinding.GridViewItemBinding
import com.example.movielite.network.MovieApi
import com.example.movielite.network.repository.MovieRepository
import kotlinx.coroutines.launch

class MainViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private val
    private fun getPopularMovies() {
        viewModelScope.launch {
            movieRepository.getPopularMovies("api_Key")
        }
    }
}