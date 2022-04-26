package com.mihua.ljxbao.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gas.base.BaseViewModel
import com.mihua.ljxbao.bean.Account
import com.mihua.ljxbao.bean.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel() {

    private val _loginLiveData = MutableLiveData<Resource<Account>>()
    val loginLiveData: LiveData<Resource<Account>> get() = _loginLiveData

}

