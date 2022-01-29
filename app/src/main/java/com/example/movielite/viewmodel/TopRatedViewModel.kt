package com.example.movielite.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielite.network.repository.TopRatedRepository
import com.example.movielite.response.recomendedresponse.Recommended
import com.example.movielite.response.toprated.TopRated
import kotlinx.coroutines.launch
import java.lang.Exception

class TopRatedViewModel(private val topRatedRepository: TopRatedRepository) : ViewModel() {

    private val TAG = TopRatedViewModel::class.java.simpleName
    private val _topRatedLiveData = MutableLiveData<List<TopRated>?>()
    val topRatedLiveData: LiveData<List<TopRated>?>
    get() = _topRatedLiveData

    init {
        getRecommendedMovies()
    }
    private fun getRecommendedMovies() {
        viewModelScope.launch {
            try {
                _topRatedLiveData.value = topRatedRepository.getTopRatedMovies(
                    TMDB_API_KEY, "en-US", 1).topRated
                Log.d(TAG, "${_topRatedLiveData.value}")
            }
            catch (e: Exception) {
                Log.d(TAG, e.message.toString())
            }
        }
    }
}