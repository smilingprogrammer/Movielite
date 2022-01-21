package com.example.movielite.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielite.MovieDetail
import com.example.movielite.network.repository.MovieDetailRepository
import kotlinx.coroutines.launch

class MovieDetailFragmentViewModel(private val movieDetailRepository: MovieDetailRepository): ViewModel() {

    private val TAG = MovieDetailFragmentViewModel::class.java.simpleName

    private val _popularMoviesDetailLiveData = MutableLiveData<List<MovieDetail>>()
    val popularMoviesDetailLiveData: LiveData<List<MovieDetail>>
        get() = _popularMoviesDetailLiveData
//    init {
//        getPopularMovieDetails(movieId = id)
//    }
    fun getPopularMovieDetails(movieId: Int) {

        try {
            viewModelScope.launch {
                _popularMoviesDetailLiveData.value = movieDetailRepository.getMovieDetails(movieId, TMDB_API_KEY).movieDetail
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
    }
}