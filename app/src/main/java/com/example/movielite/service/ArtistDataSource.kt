package com.example.movielite.service

import androidx.paging.PagingSource
import com.example.movielite.network.MovieApiService
import com.example.movielite.response.artistresponse.Artist
import com.example.movielite.viewmodel.TMDB_API_KEY

class ArtistDataSource(private val movieApiService: MovieApiService,
    private val artistType: ArtistType)
    :PagingSource<Int, Artist>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Artist> {
        return try {
            val nextPage = params.key ?: 1
            val response = loadPage(nextPage)

            LoadResult.Page(
                data = response,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (t: Throwable) {
            LoadResult.Error(t)
        }
    }

    private suspend fun loadPage(
        page: Int?
    ): List<Artist> {
        val response = when (artistType){
            ArtistType.POPULAR -> movieApiService.getPopularArtist(TMDB_API_KEY, page)
            ArtistType.TRENDING_TODAY -> movieApiService.getTrendingArtists("person", "day", TMDB_API_KEY, page)
            ArtistType.TRENDING_THIS_WEEK -> movieApiService.getTrendingArtists("person", "week", TMDB_API_KEY, page)
        }
        return response.artist!!
    }

}