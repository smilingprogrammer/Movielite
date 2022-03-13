package com.example.movielite.response.castandcrew

import com.squareup.moshi.Json

data class Cast(

    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "credit_id")
    val creditId: String,

    @Json(name = "character")
    val character: String?,

    @Json(name = "order")
    val order: Int?,

    @Json(name = "profile_path")
    val profilePath: String?

)
