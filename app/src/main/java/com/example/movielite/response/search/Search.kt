package com.example.movielite.response.search

import com.example.movielite.service.Response
import com.squareup.moshi.Json

data class Search(
    @Json(name = "page")
    val page: Int?,
    @Json(name = "results")
    val results: List<SearchResult>,
    @Json(name = "total_pages")
    val totalPages: Int?,
    @Json(name = "total_results")
    val totalResults: Int?
) : Response
