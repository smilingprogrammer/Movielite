package com.example.movielite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.movielite.network.MovieApi
import com.example.movielite.service.SeriesDataSource
import com.example.movielite.service.SeriesType

class AllSeriesViewModel : ViewModel() {

    private val apiService = MovieApi.retrofitService

    private fun getSeriesDataSource(seriesType: SeriesType) = SeriesDataSource(apiService, seriesType)

    private val pagingConfig = PagingConfig(pageSize = 20, initialLoadSize = 3, enablePlaceholders = true)

    fun pagingFlow(seriesType: SeriesType) = Pager(pagingConfig){
        getSeriesDataSource(seriesType)
    }.flow.cachedIn(viewModelScope)
}