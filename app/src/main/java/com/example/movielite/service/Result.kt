package com.example.movielite.service

import com.example.movielite.network.repository.APIResponse

sealed class Result<out T> {
    data class Success<out T>(val value: T): Result<T>()
    data class Error(val code: Int? = null, val error: APIResponse? = null) : Result<Nothing>()
    object NetworkError : Result<Nothing>()
}