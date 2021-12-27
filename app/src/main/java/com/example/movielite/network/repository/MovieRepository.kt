package com.example.movielite.network.repository

import com.example.movielite.main.TMDB_API_KEY
import com.example.movielite.network.MovieApiService

class MovieRepository(private val movieApiService: MovieApiService) {

    suspend fun getPopularMovies() = movieApiService.getPopularMovies(
        TMDB_API_KEY, "en-US",
    1)
}