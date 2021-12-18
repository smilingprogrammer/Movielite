package com.example.movielite.overview

import android.icu.text.CaseMap
import android.media.Image
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielite.network.ModelImage
import com.example.movielite.network.MovieApi
import com.example.movielite.network.MovieApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status
    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMovieImage()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [LiveData].
     */
    fun getMovieImage() {
        viewModelScope.launch {
            try {
                val listResult = MovieApi.retrofitService.getPhotos()
                _status.value = "Success: ${listResult.size} Mars photos retrieved"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
//    fun getMovieImage(lang: String):LiveData<String>
//    {
//        CoroutineScope(Dispatchers.IO).launch {
//            val listResult = MovieApi.retrofitService.getImage("en").await()
//            _status.value = listResult.Image
//        }
//       return _status
//    }
//
//    fun getName():LiveData<String> {
//        CoroutineScope(Dispatchers.IO).launch {
//            val listResult = MovieApi.retrofitService.getImage().await()
//            _status.postValue(listResult.Title)
//        }
//        return _status
//    }
}