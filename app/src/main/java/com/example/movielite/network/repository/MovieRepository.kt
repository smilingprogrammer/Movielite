package com.example.movielite.network.repository

import com.example.movielite.network.MovieApiService
import com.example.movielite.response.shows.Series
import com.example.movielite.service.BaseResponse

class MovieRepository(private val movieApiService: MovieApiService) {

    suspend fun getPopularMovies(apikey: String, language: String, page:Int) = movieApiService.getPopularMovies(
        apikey, language, page
    )
}