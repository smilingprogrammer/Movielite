package com.example.movielite.response.toprated

import com.squareup.moshi.Json

data class TopRatedResponse(
    val page: Int,
    @Json(name = "results")
    val topRated: List<TopRated>,
    val total_pages: Int,
    val total_results: Int
)