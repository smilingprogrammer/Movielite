package com.example.movielite.response.moviedetailresponse

import com.example.movielite.response.castandcrew.Credits
import com.example.movielite.response.graphics.Graphic
import com.squareup.moshi.Json

data class MovieDetail(

    @Json(name = "adult") val adult: Boolean,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "belongs_to_collection") val belongsToCollection: Collection?,
    @Json(name = "budget") val budget: Int,
    @Json(name = "genres") val genres: List<Genre>,
    @Json(name = "homepage") val homepage: String?,
    @Json(name = "id") val id: Int,
    @Json(name = "imdb_id") val imdbId: String?,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "overview") val overview: String?,
    @Json(name = "popularity") val popularity: Double,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "production_companies") val productionCompanies: List<ProductionCompany>,
    @Json(name = "production_countries") val productionCountries: List<ProductionCountry>,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "revenue") val revenue: Long,
    @Json(name = "runtime") val runtime: Int?,
    @Json(name = "spoken_languages") val spokenLanguages: List<SpokenLanguage>,
    @Json(name = "status") val status: String,
    @Json(name = "tagline") val tagline: String?,
    @Json(name = "title") val title: String,
    @Json(name = "video") val video: Boolean,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "vote_count") val voteCount: Int,
    // AppendToResponse
    @Json(name = "images") val images: Graphic,
    @Json(name = "credits") val credits: Credits,
    @Json(name = "videos") val videos: Videos
) {
    data class Collection(
        @Json(name = "id") val id: Int,
        @Json(name = "name") val name: String,
        @Json(name = "poster_path") val posterPath: String?,
        @Json(name = "backdrop_path") val backdropPath: String?
    )
}
//data class MovieDetail(
//    val adult: Boolean?,
//    val backdrop_path: String?,
//    val belongs_to_collection: Any?,
//    val budget: Int?,
//    val genres: List<Genre>?,
//    val homepage: String?,
//    val id: Int?,
//    val imdb_id: String?,
//    val original_language: String?,
//    val original_title: String?,
//    val overview: String?,
//    val popularity: Double?,
//    val poster_path: String?,
//    val production_companies: List<ProductionCompany>?,
//    val production_countries: List<ProductionCountry>?,
//    val release_date: String?,
//    val revenue: Int?,
//    val runtime: Int?,
//    val spoken_languages: List<SpokenLanguage>?,
//    val status: String?,
//    val tagline: String?,
//    val title: String?,
//    @Json(name = "credits") val credits: Credits,
//    val video: Boolean?,
//    val videos: Videos?,
//    val vote_average: Double?,
//    val vote_count: Int?
//)