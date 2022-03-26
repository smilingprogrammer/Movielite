package com.example.movielite.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.movielite.response.popularresponse.Movie
import com.example.movielite.network.repository.MovieRepository
import com.example.movielite.response.shows.Series
import com.example.movielite.service.SeriesType
import kotlinx.coroutines.launch

const val TMDB_API_KEY = "b7c2120334a858f18ce8ddd09946c6ad"
class MainViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private val TAG = MainViewModel::class.java.simpleName
    private val _popularMoviesLiveData = MutableLiveData<List<Movie>>()
    val popularMoviesLiveData:LiveData<List<Movie>>
    get() = _popularMoviesLiveData

    private val _popularSeriesLiveData = MutableLiveData<List<Series>>()
    val popularSeriesLiveData: LiveData<List<Series>>
    get() = _popularSeriesLiveData

    init {
        getPopularMovies()
//        getPopularSeries()
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
//    private fun getPopularSeries() {
//        viewModelScope.launch {
//            try {
//                _popularSeriesLiveData.value = movieRepository.getPopularSeries(
//                    TMDB_API_KEY, 1
//                ).series
//                Log.d(TAG, "${_popularSeriesLiveData.value}")
//            } catch (e: Exception) {
//                Log.d(TAG, e.message.toString())
//            }
//        }
//    }
}