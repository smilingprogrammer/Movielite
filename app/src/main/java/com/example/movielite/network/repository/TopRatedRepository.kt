package com.example.movielite.network.repository

import com.example.movielite.network.MovieApiService

class TopRatedRepository(private val movieApiService: MovieApiService) {

    suspend fun getTopRatedMovies(apiKey: String, language: String, page: Int) = movieApiService.getTopRatedMovies(
        apiKey, language, page
    )
}