package com.mihua.data.login

import com.mihua.bean.Account
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {
    @FormUrlEncoded
    @POST("user/login")
    suspend fun getRemoteUser(
        @Field("username") userName: String,
        @Field("password") pwd: String
    ): Account
}