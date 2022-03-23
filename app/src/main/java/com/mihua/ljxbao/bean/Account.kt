package com.mihua.ljxbao.bean

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Account(
    val admin: Boolean = false,
    val coinCount: Int = 0,
    val email: String? = "",
    val icon: String? = "",
    val id: Int = 0,
    val nickname: String? = "",
    val password: String? = "",
    val publicName: String? = "",
    val token: String? = "",
    val type: Int = 0,
    val username: String? = ""
)