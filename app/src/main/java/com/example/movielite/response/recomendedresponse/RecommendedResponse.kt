package com.example.movielite.response.recomendedresponse

data class RecommendedResponse(
    val page: Int?,
    val recommended: List<Recommended>?,
    val total_pages: Int?,
    val total_results: Int?
)