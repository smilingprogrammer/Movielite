package com.example.movielite.network.repository

import com.example.movielite.network.MovieApiService

class MovieDetailRepository(private val movieApiService: MovieApiService) {

    suspend fun getMovieDetails(apiKey: String, language: String, response: String) = movieApiService.getMovieDetails(
        apiKey, language, response
    )
}