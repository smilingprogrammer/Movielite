package com.example.movielite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.movielite.network.MovieApi
import com.example.movielite.service.SearchDataSource
import retrofit2.Retrofit
import timber.log.Timber

class MultiSearchViewModel: ViewModel() {

    private val movieApiService = MovieApi.retrofitService

    private val queryData = MutableLiveData<String>()
    val liveQuery: LiveData<String> get() = queryData

    private fun getDataSource(query: String, adult: Boolean) = SearchDataSource(
        movieApiService, query, adult
    )

    private val pagingConfig = PagingConfig(pageSize = 20, initialLoadSize = 10, enablePlaceholders = false)

    fun getSearchPaging(query: String, adult: Boolean) = Pager(pagingConfig) {
        getDataSource(query, adult)
    }.flow.cachedIn(viewModelScope)

    fun updateSearch(query: String) {
        Timber.d(query)
        if (query == queryData.value) return
        if (query.isNotEmpty() && query.isNotBlank()) {
            query.trim().apply {
                queryData.postValue(query)
            }
        }
    }
}