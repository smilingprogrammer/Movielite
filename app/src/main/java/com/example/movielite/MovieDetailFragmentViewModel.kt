package com.example.movielite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielite.main.TMDB_API_KEY
import com.example.movielite.network.Movie
import com.example.movielite.network.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieDetailFragmentViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private val TAG = MovieDetailFragmentViewModel::class.java.simpleName

    private val _popularMoviesDetailLiveData = MutableLiveData<List<Movie>>()
    val popularMoviesDetailLiveData: LiveData<List<Movie>>
        get() = _popularMoviesDetailLiveData
    fun getPopularMovieDetail(id: Int) {

        try {
            viewModelScope.launch {
                _popularMoviesDetailLiveData.value = movieRepository.getPopularMovies(TMDB_API_KEY, "en-US", 1).movie
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
    }
}