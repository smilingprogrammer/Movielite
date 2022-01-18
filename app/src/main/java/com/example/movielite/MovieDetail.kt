package com.example.movielite

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import java.io.Serializable

data class MovieDetail(
    @Json(name = "id")
    var id: String? = null,
    @Json(name = "homepage")
    var homePage: String? = null,
    @Json(name = "imdb_id")
    var imdbId: String = "",
    @Json(name = "revenue")
    var revenue: Int = 0,
    @Json(name = "runtime")
    var runtime: Int = 0,
    @Json(name = "status")
    var releaseStatus: String? = null,
    @Json(name = "tagline")
    var tagLine: String? = null,
    @Json(name = "budget")
    var budget: Int = 0,
    @Json(name = "overview")
    var overview: String? = null,
    @Json(name = "backdrop_path")
    var backdropPath: String? = null,
    @Json(name = "title")
    var title: String? = null
//    @Json(name = "videos")
//    var videosResult: MovieVideosRequest? = null
): Serializable
