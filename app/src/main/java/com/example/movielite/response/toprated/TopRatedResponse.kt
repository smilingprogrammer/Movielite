package com.example.movielite.response.toprated

data class TopRatedResponse(
    val page: Int?,
    val topRated: List<TopRated>?,
    val total_pages: Int?,
    val total_results: Int?
)