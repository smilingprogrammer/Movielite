package com.example.movielite.network.repository

import com.example.movielite.network.MovieApi
import com.example.movielite.response.shows.SeriesResponse
import com.example.movielite.service.BaseResponse
import com.example.movielite.service.Result
import com.example.movielite.viewmodel.TMDB_API_KEY
import okhttp3.MediaType

class SeriesRepository: BaseRepository() {

    private val apiService = MovieApi.retrofitService

    enum class TimeFrame(val path: String) {
        DAY("day"),
        WEEK("week")
    }

    private enum class MediaType(val path: String) {
        ALL("all"),
        MOVIE("movie"),
        SERIES("tv"),
        ARTIST("person")
    }

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

    suspend fun getTrendingSeries(
        time: TimeFrame,
        page: Int?
    ): Result<SeriesResponse> {
        return coroutineHandler(dispatcher) {
            apiService.getTrendingSeries(
                MediaType.SERIES.path,
                time.path,
                TMDB_API_KEY,
                page
            )
        }
    }
}