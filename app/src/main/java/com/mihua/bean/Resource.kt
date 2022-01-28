package com.mihua.bean

// A generic class that contains data and status about loading this data.
sealed class Resource<out T> {

    companion object {
        fun <T> success(result: T): Resource<T> = Success(result)
    }

    data class Success<out T>(val data: T) : Resource<T>()
    object Loading : Resource<Any>()
    data class Failure(val code: Int = -1, val msg: String = "") : Resource<Any>()

}