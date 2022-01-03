package com.example.movielite.network.repository

import com.example.movielite.network.MovieApiService

class MovieDetailRepository(private val movieApiService: MovieApiService) {

    suspend fun getMovieDetails(movieId: String, apiKey: String, language: String, response: String) = movieApiService.getMovieDetails(
        movieId, apiKey, language, response
    )
}