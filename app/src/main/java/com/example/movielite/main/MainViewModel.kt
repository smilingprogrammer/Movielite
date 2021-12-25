package com.example.movielite.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.movielite.databinding.GridViewItemBinding
import com.example.movielite.network.Movie
import com.example.movielite.network.MovieApi
import com.example.movielite.network.repository.MovieRepository
import kotlinx.coroutines.launch

class MainViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private val TAG = MainViewModel::class.java.simpleName
    private val _popularMoviesLiveData = MutableLiveData<List<Movie>>()
    val popularMoviesLiveData:LiveData<List<Movie>>
    get() = _popularMoviesLiveData

    init {
        getPopularMovies()
    }
    private fun getPopularMovies() {
        viewModelScope.launch {
            try {
                _popularMoviesLiveData.value = movieRepository.getPopularMovies("api_Key").movie
                Log.d(TAG, "${_popularMoviesLiveData.value}")
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
            }
        }
    }
}