package com.example.movielite.network

import retrofit2.Retrofit

private const val BASE_URL = ""
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
class movieapiservice {
}