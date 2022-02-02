package com.example.movielite.response.upcoming

data class UpcomingResponse(
    val dates: Dates?,
    val page: Int?,
    val upcomings: List<Upcoming>?,
    val total_pages: Int?,
    val total_results: Int?
)