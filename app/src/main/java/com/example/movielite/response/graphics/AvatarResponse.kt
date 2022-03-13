package com.example.movielite.response.graphics

import com.example.movielite.response.graphics.GraphicDetails
import com.squareup.moshi.Json

data class AvatarResponse(

    @Json(name = "profiles")
    val profiles: List<GraphicDetails>

)
