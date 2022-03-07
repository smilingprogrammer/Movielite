package com.example.movielite.network

import com.example.movielite.response.artistresponse.Artist
import com.example.movielite.response.artistresponse.ArtistResponse
import com.example.movielite.response.moviedetailresponse.MovieDetail
import com.example.movielite.response.popularresponse.MovieResponse
import com.example.movielite.response.recomendedresponse.RecommendedResponse
import com.example.movielite.response.toprated.TopRated
import com.example.movielite.response.toprated.TopRatedResponse
import com.example.movielite.response.upcoming.UpcomingResponse
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

    @GET("person/popular")
    suspend fun getPopularArtist(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int) : ArtistResponse

    @GET("/movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int) : UpcomingResponse

}
object MovieApi {
    val retrofitService : MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
   }
}