package com.mihua.ljxbao.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gas.base.BaseViewModel
import com.mihua.ljxbao.bean.Account
import com.mihua.ljxbao.bean.Resource
import com.mihua.ljxbao.data.login.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: LoginRepository) : BaseViewModel() {


    companion object{
       const val TAG = "TAG"
    }
    private val _loginLiveData = MutableLiveData<Resource<Account>>()
    val loginLiveData: LiveData<Resource<Account>> get() = _loginLiveData
    fun login(userName: String, passWord: String) {
        viewModelScope.launch {
            _loginLiveData.value= Resource.Loading
            try {
                _loginLiveData.value = Resource.success(repo.login(userName, passWord))
            } catch (e: Exception) {
                _loginLiveData.value = Resource.fail(-1, "登录错误")
            }
        }
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(10, TimeUnit.SECONDS)

        builder.addInterceptor(Interceptor { chain ->
            Log.d(TAG, "Interceptor url:" + chain.request().url.toString())
            chain.proceed(chain.request())
        })
        builder.addNetworkInterceptor(Interceptor { chain ->
            Log.d(TAG, "NetworkInterceptor url:" + chain.request().url.toString())
            chain.proceed(chain.request())
        })

        val client: OkHttpClient = builder.build()

        val request: Request = Request.Builder()
            .url("https://www.baidu.com")
            .build()
        val call: Call = client.newCall(request)

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, "onFailure: " + e.message)
            }
            override fun onResponse(call: Call, response: Response) {
                Log.d(TAG, "response:" + response.body?.string())
            }
        })

        val response = call.execute()
    }
}

