package com.mihua.ui.login.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gas.base.BaseViewModel
import com.mihua.ui.login.model.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: LoginRepository) : BaseViewModel() {

    private val _loginLiveData = MutableLiveData<User>()
    val loginLiveData: LiveData<Resource<LoginResponse>> get() = _loginLiveData

    fun doLogin(userName: String, passWord: String) {
        viewModelScope.launch {
            loginLiveDataPrivate.value = Resource.Loading()
            wrapEspressoIdlingResource {
                dataRepository.doLogin(loginRequest = LoginRequest(userName, passWord)).collect {
                    loginLiveDataPrivate.value = it
                }
            }
        }
    }


}
