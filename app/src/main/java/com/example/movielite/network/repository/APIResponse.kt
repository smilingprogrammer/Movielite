package com.example.movielite.network.repository

import com.squareup.moshi.Json

data class APIResponse (
    @Json(name = "status_message")
    val errorMessage: String?,

    @Json(name = "status_code")
    val errorCode: Int?
    )