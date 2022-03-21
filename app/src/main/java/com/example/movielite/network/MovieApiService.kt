package com.example.movielite.network

import com.example.movielite.response.artistresponse.ArtistInfo
import com.example.movielite.response.artistresponse.ArtistResponse
import com.example.movielite.response.moviedetailresponse.MovieDetail
import com.example.movielite.response.popularresponse.MovieResponse
import com.example.movielite.response.search.Search
import com.example.movielite.response.search.SearchResult
import com.example.movielite.response.shows.Series
import com.example.movielite.response.shows.SeriesDetails
import com.example.movielite.response.shows.SeriesResponse
import com.example.movielite.response.toprated.TopRatedResponse
import com.example.movielite.response.upcoming.UpcomingResponse
import com.example.movielite.service.BaseResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

//calling the api
interface MovieApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String,
    @Query("language") language: String,
    @Query("page") page: Int) : MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int,
    @Query("api_key") apiKey: String,
    @Query("language") language: String,
    @Query("append_to_response") appendToResponse: String) : MovieDetail

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
    @Query("api_key") apiKey: String,
    @Query("language") language: String,
    @Query("page") page: Int) : TopRatedResponse

    @GET("/movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int) : UpcomingResponse

    @GET("person/popular")
    suspend fun getPopularArtist(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int) : ArtistResponse

    @GET("person/{person_id}")
    suspend fun getArtistDetails(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String,
        @Query("append_to_response") appendToResponse: String
    ): ArtistInfo

    /*TV Series*/
    @GET("tv/popular")
    suspend fun getPopularSeries(
        @Query("api_key") apiKey: String?,
        @Query("page") page: Int?
    ): SeriesResponse

    @GET("tv/{tv_id}")
    suspend fun getSeriesByID(
        @Path("tv_id") tvId: Int?,
        @Query("api_key") apiKey: String?,
        @Query("append_to_response") appendToResponse: String?
    ): SeriesDetails

    /*Search*/
    @GET("search/multi")
    suspend fun search(
        @Query("api_key") apiKey: String?,
        @Query("query") query: String?,
        @Query("page") page: Int?,
        @Query("include_adult") isAdult: Boolean
    ): Search

}
object MovieApi {
    val retrofitService : MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
   }
}