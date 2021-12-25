package com.example.movielite.network.repository

import com.example.movielite.network.MovieApiService

class MovieRepository(private val movieApiService: MovieApiService) {

    suspend fun getPopularMovies(apiKey: String) = movieApiService.getPopularMovies(apiKey, "en-US",
    2, "", "2")
}