package com.example.movielite.service

import androidx.paging.PagingSource
import com.example.movielite.network.MovieApiService
import com.example.movielite.response.shows.Series
import com.example.movielite.response.shows.SeriesResponse
import com.example.movielite.viewmodel.TMDB_API_KEY

class SeriesDataSource(private val movieApiService: MovieApiService, private val seriesType: SeriesType)
    :PagingSource<Int, Series>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Series> {
        return try {

            val newPage = params.key ?: 1
            val response = loadPage(page = newPage)

            LoadResult.Page(
                data = response,
                prevKey = if (newPage == 1) null else newPage - 1,
                nextKey = newPage + 1
            )
        } catch (t: Throwable) {
            LoadResult.Error(t)
        }
    }

    private suspend fun loadPage(
        page: Int
    ): List<Series> {
        val response = when (seriesType) {
            SeriesType.POPULAR -> movieApiService.getPopularSeries(TMDB_API_KEY, page)
            SeriesType.TOP_RATED -> movieApiService.getTopRatedSeries(TMDB_API_KEY, page)
            SeriesType.NOW_SHOWING -> movieApiService.getSeriesNowShowing(TMDB_API_KEY, page)
            SeriesType.SHOWING_TODAY -> movieApiService.getSeriesShowingToday(TMDB_API_KEY, page)
            SeriesType.TRENDING_TODAY -> movieApiService.getTrendingSeries(
                "tv",
                "day",
                TMDB_API_KEY,
                page
            )
            SeriesType.TRENDING_THIS_WEEK -> movieApiService.getTrendingSeries(
                "tv",
                "week",
                TMDB_API_KEY,
                page
            )
        }
        return response.series
    }

}