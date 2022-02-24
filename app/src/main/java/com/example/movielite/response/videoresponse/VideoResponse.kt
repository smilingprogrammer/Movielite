package com.example.movielite.response.videoresponse

import com.squareup.moshi.Json

data class VideoResponse(
    val id: Int?,

    @Json(name = "results")
    val videos: List<Video>?
)