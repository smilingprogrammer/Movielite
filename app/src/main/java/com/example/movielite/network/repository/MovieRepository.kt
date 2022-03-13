package com.example.movielite.network.repository

import com.example.movielite.network.MovieApiService

class MovieRepository(private val movieApiService: MovieApiService) {

    suspend fun getPopularMovies(apikey: String, language: String, page:Int) = movieApiService.getPopularMovies(
        apikey, language, page
    )
    suspend fun getPopularSeries(apikey: String, page: Int) = movieApiService.getPopularSeries(
        apikey, page
    )
}