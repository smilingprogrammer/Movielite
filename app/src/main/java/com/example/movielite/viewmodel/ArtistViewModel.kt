package com.example.movielite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.movielite.network.MovieApi
import com.example.movielite.service.ArtistDataSource
import com.example.movielite.service.ArtistType

class ArtistViewModel: ViewModel() {

    private val apiService = MovieApi.retrofitService

    private val artistDataSource = ArtistDataSource(
        apiService,
        ArtistType.POPULAR
    )

    private val pagingConfig = PagingConfig(
        pageSize = 20, initialLoadSize = 5, enablePlaceholders = true
    )

    val artistPaging = Pager(pagingConfig) {
        artistDataSource
    }.flow.cachedIn(viewModelScope)
}