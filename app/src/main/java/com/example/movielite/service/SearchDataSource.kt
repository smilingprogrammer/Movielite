package com.example.movielite.service

import androidx.paging.PagingSource
import com.example.movielite.network.MovieApiService
import com.example.movielite.response.search.SearchResult
import com.example.movielite.viewmodel.TMDB_API_KEY
import timber.log.Timber

class SearchDataSource(
    private val movieApiService: MovieApiService,
    private var query: String,
    private var includeAdult: Boolean)
    : PagingSource<Int, SearchResult>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResult> {
        return try {
            val nextPage = params.key?: 1
            val response = loadPage(page = nextPage, includeAdult)

            LoadResult.Page(
                data = response,
                prevKey = if (nextPage == 1) null else nextPage -1,
                nextKey = nextPage + 1
            )
        } catch (t: Throwable) {
            Timber.e(t.localizedMessage)
            LoadResult.Error(t)
        }
    }

    private suspend fun loadPage(
        page: Int,
        adult: Boolean
    ): List<SearchResult> {
        Timber.d("$page")
        val responseData = movieApiService.search(TMDB_API_KEY, query, page, adult)
        return responseData.results
    }
}