package com.example.movielite.response.shows

import com.squareup.moshi.Json

data class SeriesResponse(
    @Json(name = "results")
    val series: List<Series>
)