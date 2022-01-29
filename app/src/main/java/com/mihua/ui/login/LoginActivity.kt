package com.mihua.ui.login

import android.view.View
import androidx.activity.viewModels
import com.gas.base.activity.BaseVMActivity
import com.mihua.R
import com.mihua.bean.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseVMActivity() {


    private val mVm : LoginViewModel by viewModels()
    override fun layoutId()= R.layout.activity_login
    override fun initData() {
        findViewById<View>(R.id.login).setOnClickListener {
            mVm.login("11","22")
        }
        mVm.loginLiveData.observe(this){
            when (it) {
                is Resource.Failure -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {}
            }
        }
    }
}
