package com.mihua.data.login

import com.mihua.bean.Account
import javax.inject.Inject

class LoginRepository @Inject constructor(private val remoteService: LoginService) {
    suspend fun login(userName: String, pwd: String): Account {
        return remoteService.getRemoteUser(userName, pwd)
    }
}