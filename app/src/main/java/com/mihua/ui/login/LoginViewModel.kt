package com.mihua.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gas.base.BaseViewModel
import com.mihua.bean.Account
import com.mihua.bean.Resource
import com.mihua.data.login.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: LoginRepository) : BaseViewModel() {

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
    }
}

