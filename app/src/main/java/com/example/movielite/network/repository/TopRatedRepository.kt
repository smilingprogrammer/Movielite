package com.example.movielite.network.repository

import com.example.movielite.network.MovieApiService

class TopRatedRepository(private val movieApiService: MovieApiService) {

    suspend fun getRecommendedMovies(apiKey: String, language: String, page: Int) = movieApiService.getRecommendedMovies(
        apiKey, language, page
    )
}