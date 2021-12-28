package com.example.movielite.network.repository

import com.example.movielite.main.TMDB_API_KEY
import com.example.movielite.network.MovieApiService

class MovieRepository(private val movieApiService: MovieApiService) {

    suspend fun getPopularMovies(apikey: String, language: String, page:Int) = movieApiService.getPopularMovies(
        apikey, language, page
    )
}