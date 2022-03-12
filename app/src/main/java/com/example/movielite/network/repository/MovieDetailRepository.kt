package com.example.movielite.network.repository

import com.example.movielite.network.MovieApiService

class MovieDetailRepository(private val movieApiService: MovieApiService) {

    suspend fun getMovieDetails(
        movieId: Int,
        apiKey: String,
        language: String,
        appendToResponse: String) = movieApiService.getMovieDetails(
        movieId, apiKey, language, appendToResponse
    )

    suspend fun getArtistDetails(
        personId: Int,
        apiKey: String) = movieApiService.getArtistDetails(
        personId, apiKey
    )
}