package com.example.movielite.response.artistresponse

data class ArtistResponse(
    val page: Int?,
    val artists: List<Artist>?,
    val total_pages: Int?,
    val total_results: Int?
)