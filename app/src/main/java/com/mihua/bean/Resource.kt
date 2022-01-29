package com.mihua.bean

// A generic class that contains data and status about loading this data.
sealed class Resource<out T> {

    companion object {
        fun <T> success(result: T): Resource<T> = Success(result)
        fun fail(code: Int = -1, msg: String = ""):Resource<Nothing> = Failure(code, msg)
    }

    data class Success<out T>(val data: T) : Resource<T>()
    object Loading : Resource<Nothing>()
    data class Failure(val code: Int = -1, val msg: String = "") : Resource<Nothing>()

}