package com.example.movielite.response.upcoming

import com.squareup.moshi.Json

data class UpcomingResponse(
    val dates: Dates?,
    val page: Int?,
    @Json(name = "results")
    val upcoming: List<Upcoming>?,
    val total_pages: Int?,
    val total_results: Int?
)