package com.example.movielite.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielite.response.popularresponse.Movie
import com.example.movielite.network.repository.MovieRepository
import kotlinx.coroutines.launch

const val TMDB_API_KEY = "b7c2120334a858f18ce8ddd09946c6ad"
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
                _popularMoviesLiveData.value = movieRepository.getPopularMovies(
                    TMDB_API_KEY, "en-US", 1).movie
                Log.d(TAG, "${_popularMoviesLiveData.value}")
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
            }
        }
    }
}