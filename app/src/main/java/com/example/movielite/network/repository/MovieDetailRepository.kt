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

    suspend fun getSeriesDetails(
        tvId: Int,
        apiKey: String,
        appendToResponse: String) = movieApiService.getSeriesByID(
        tvId, apiKey, appendToResponse
    )

    suspend fun getArtistDetails(
        personId: Int,
        apiKey: String,
        appendToResponse: String) = movieApiService.getArtistDetails(
        personId, apiKey, appendToResponse
    )

    suspend fun search(
        apiKey: String,
        query: String,
        page: Int,
        isAdult: Boolean) = movieApiService.search(
            apiKey, query, page, isAdult
        )
}