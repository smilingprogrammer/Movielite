package com.example.movielite.service

import com.squareup.moshi.Json

data class BaseResponse<T: Response> (

    @Json(name = "page")
    val page: Int,

    @Json(name = "results")
    val results: List<T>,

    @Json(name = "total_results")
    val totalResults: Int,

    @Json(name = "total_pages")
    val totalPages: Int
)