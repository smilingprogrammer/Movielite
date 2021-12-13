package com.example.movielite.overview

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
    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [LiveData].
     */
    fun getMovieImage(lang: String):LiveData<String>
    {
        CoroutineScope(Dispatchers.IO).launch {
            val listResult = MovieApi.retrofitService.getImage(lang).await()
            _status.value = listResult.Image
        }
       return _status
    }

    fun getName():LiveData<String> {
        CoroutineScope(Dispatchers.IO).launch {
            val listResult = MovieApi.retrofitService.getNames().await()
            _status.postValue(listResult.Title)
        }
        return _status
    }
}