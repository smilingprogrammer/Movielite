package com.example.movielite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielite.main.TMDB_API_KEY
import com.example.movielite.network.Movie
import com.example.movielite.network.repository.MovieDetailRepository
import com.example.movielite.network.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieDetailFragmentViewModel(private val movieDetailRepository: MovieDetailRepository): ViewModel() {

    private val TAG = MovieDetailFragmentViewModel::class.java.simpleName

    private val _popularMoviesDetailLiveData = MutableLiveData<List<Movie>>()
    val popularMoviesDetailLiveData: LiveData<List<Movie>>
        get() = _popularMoviesDetailLiveData
    fun getPopularMovieDetails(id: Int) {

        try {
            viewModelScope.launch {
                _popularMoviesDetailLiveData.value = movieDetailRepository.getMovieDetails(TMDB_API_KEY, TMDB_API_KEY, "en-US", "image").movie
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
    }
}