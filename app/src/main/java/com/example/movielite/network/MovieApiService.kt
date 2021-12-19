package com.example.movielite.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://imdb-api.com/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

//calling the api
interface MovieApiService {

    @GET("{lang}/API/Images/k_749nmmqz/tt1375666")
    suspend fun getImages(@Query("")) : List<ModelImage>
//    @GET("{lang}/API/Images/k_749nmmqz/tt1375666")
//    suspend fun getImage(
//        @Path("lang") lang: String) : Deferred<ModelImage>
//
//    @GET("Images")
//    suspend fun getNames() : Deferred<ModelImage>
}
object MovieApi {
    val retrofitService : MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
   }
}