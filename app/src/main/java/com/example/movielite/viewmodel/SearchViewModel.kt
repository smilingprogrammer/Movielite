package com.example.movielite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    private val _query = MutableLiveData<String>()
    val query: LiveData<String> get() = _query

    fun updateSearchQuery(query: String) {
        _query.postValue(query)
    }

}