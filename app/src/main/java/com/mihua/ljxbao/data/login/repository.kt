package com.mihua.ljxbao.data.login

import com.mihua.ljxbao.bean.Account
import javax.inject.Inject

class LoginRepository @Inject constructor(private val remoteService: LoginService) {
    suspend fun login(userName: String, pwd: String): Account {
        return remoteService.getRemoteUser(userName, pwd)
    }
}