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

    fun doLogin(userName: String, passWord: String) {
        viewModelScope.launch {
//            dataRepository.login(userName, passWord)
//                loginLiveDataPrivate.value = it
//            }
        }
    }


}
