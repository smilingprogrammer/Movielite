package com.example.movielite.response.artistresponse

import com.squareup.moshi.Json

data class ArtistResponse(
    val page: Int?,
    @Json(name = "results")
    val artist: List<Artist>?,
    val total_pages: Int?,
    val total_results: Int?
)