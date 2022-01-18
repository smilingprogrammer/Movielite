package com.example.movielite.network

import android.provider.SyncStateContract
import com.squareup.moshi.Json
import java.io.Serializable

data class Movie(
//    @Json(name = "api_key")
//    val title: String,
//    @Json(name = "")
//    val image: String,
//    @Json(name = "")
//    val releaseType: String

    @Json(name = "vote_count")
    var voteCount: Int? = null,
    @Json(name = "id")
    var id: Int? = null,
//    @Json(name = "video")
//    var video: Boolean? = null,
    @Json(name = "vote_average")
    var voteAverage: Float? = null,
    @Json(name = "title")
    var title: String? = null,
    @Json(name = "popularity")
    var popularity: Float? = null,
    @Json(name = "poster_path")
    var posterPath: String? = null,
    @Json(name = "original_language")
    var originalLanguage: String? = null,
    @Json(name = "original_title")
    var originalTitle: String? = null,
    @Json(name = "genre_ids")
    var genreIds: List<Int>? = null,
    @Json(name = "backdrop_path")
    var backdropPath: String? = null,
    @Json(name = "adult")
    var adult: Boolean? = null,
    @Json(name = "overview")
    var overview: String? = null,
    @Json(name = "release_date")
    var releaseDate: String? = null
//    var genreString: String = "",
//    var contentType: Int = SyncStateContract.Constants.CONTENT_MOVIE,
//    var tableName: Int? = null
    ): Serializable

