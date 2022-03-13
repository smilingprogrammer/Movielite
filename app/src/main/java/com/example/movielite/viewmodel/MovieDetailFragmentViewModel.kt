package com.example.movielite.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielite.response.moviedetailresponse.MovieDetail
import com.example.movielite.network.repository.MovieDetailRepository
import com.example.movielite.response.artistresponse.ArtistInfo
import kotlinx.coroutines.launch

class MovieDetailFragmentViewModel(private val movieDetailRepository: MovieDetailRepository): ViewModel() {

    private val TAG = MovieDetailFragmentViewModel::class.java.simpleName

    private val _popularMoviesDetailLiveData = MutableLiveData<MovieDetail>()
    val popularMoviesDetailLiveData: LiveData<MovieDetail>
        get() = _popularMoviesDetailLiveData

    private val _artistDetailsLiveData = MutableLiveData<ArtistInfo>()
    val artistDetailsLiveData: LiveData<ArtistInfo>
    get() = _artistDetailsLiveData

    fun getPopularMovieDetails(movieId: Int)  {
        try {
            viewModelScope.launch {
                _popularMoviesDetailLiveData.value = movieDetailRepository.getMovieDetails(
                    movieId, TMDB_API_KEY, "en-US", "images, reviews, credits, videos")
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
    }

    fun getArtistDetails(personId: Int) {
        try{
            viewModelScope.launch {
                _artistDetailsLiveData.value = movieDetailRepository.getArtistDetails(
                        personId, TMDB_API_KEY, "movie_credits,tv_credits,images,tagged_images"
                        )
            }
        } catch (e: java.lang.Exception){
            Log.e(TAG, e.message.toString())
        }
    }
}