package com.example.movielite.network

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://imdb-api.com/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

//calling the api
interface MovieApiService {
    @GET("{lang}/API/Images/k_749nmmqz/tt1375666")
    suspend fun getImage(
        @Path("lang") lang: String) : Deferred<ModelImage>

    @GET("")
    suspend fun getNames() : Deferred<ModelImage>
}
object MovieApi {
    val retrofitService : MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}