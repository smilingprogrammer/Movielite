package com.example.movielite.response.popularresponse

import com.example.movielite.response.popularresponse.Movie
import com.squareup.moshi.Json

data class MovieResponse(
    @Json(name = "total_pages")
    var totalPages: Int = 0,
    @Json(name = "page")
    var page: Int = 0,
    @Json(name = "results")
    var movie: List<Movie>
//    val movie: List<Movie>
)
