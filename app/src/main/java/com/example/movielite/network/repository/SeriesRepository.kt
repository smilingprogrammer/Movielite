package com.example.movielite.network.repository

import com.example.movielite.network.MovieApi
import com.example.movielite.response.shows.SeriesResponse
import com.example.movielite.service.Result

class SeriesRepository: BaseRepository() {

    private val apiService = MovieApi.retrofitService

    suspend fun getPopularSeries(apiKey: String?, page: Int?): Result<SeriesResponse> {
        return coroutineHandler(dispatcher) {
            apiService.getPopularSeries(apiKey, page)
        }
    }

    suspend fun getTopRatedSeries(apiKey: String?, page: Int?): Result<SeriesResponse> {
        return coroutineHandler(dispatcher) {
            apiService.getTopRatedSeries(apiKey, page)
        }
    }

    suspend fun getSeriesShowingToday(apiKey: String?, page: Int?): Result<SeriesResponse> {
        return coroutineHandler(dispatcher) {
            apiService.getSeriesShowingToday(apiKey, page)
        }
    }

    suspend fun getSeriesNowShowing(apiKey: String?, page: Int?): Result<SeriesResponse> {
        return coroutineHandler(dispatcher) {
            apiService.getSeriesNowShowing(apiKey, page)
        }
    }
}