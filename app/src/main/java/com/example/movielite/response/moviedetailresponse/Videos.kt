package com.example.movielite.response.moviedetailresponse

import com.squareup.moshi.Json

data class Videos(
    @Json(name = "results")
    val videoResponses: List<VideoResponse>?
)