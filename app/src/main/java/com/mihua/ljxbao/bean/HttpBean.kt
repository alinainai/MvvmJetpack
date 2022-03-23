package com.mihua.ljxbao.bean

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HttpBean<T>(
    @field:Json(name = "data") val bean: T?,
    @field:Json(name = "errorCode") val code: Int = 0,
    val errorMsg: String = ""
){
    fun isOK() = code ==1
    fun getData() = bean
}