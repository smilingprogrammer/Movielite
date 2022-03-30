package com.example.movielite.response.castandcrew

import com.squareup.moshi.Json

data class Credits(

    @Json(name = "id")
    val id: Int?,
    @Json(name = "cast")
    val casts: List<Cast>,
    @Json(name = "crew")
    val crews: List<Crew>
)