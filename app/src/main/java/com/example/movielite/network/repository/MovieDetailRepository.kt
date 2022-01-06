package com.example.movielite.network.repository

import com.example.movielite.network.MovieApiService

class MovieDetailRepository(private val movieApiService: MovieApiService) {

    suspend fun getMovieDetails(movieId: Int, apiKey: String) = movieApiService.getMovieDetails(
        movieId, apiKey
    )
}