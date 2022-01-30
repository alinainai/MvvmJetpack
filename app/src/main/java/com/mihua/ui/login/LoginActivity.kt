package com.mihua.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import com.gas.base.activity.BaseVMActivity
import com.gas.ext.app.LogExt
import com.gas.ext.app.gone
import com.gas.ext.app.visible
import com.mihua.R
import com.mihua.bean.Resource
import com.mihua.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseVMActivity() {

    private val mVm: LoginViewModel by viewModels()
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var loaderView: View

    override fun layoutId(savedInstanceState: Bundle?) = R.layout.activity_login

    override fun initData(savedInstanceState: Bundle?) {
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        loaderView = findViewById(R.id.loader_view)

        findViewById<View>(R.id.login).setOnClickListener {
            mVm.login(username.text.toString(), password.text.toString())
        }
        mVm.loginLiveData.observe(this) {
            when (it) {
                is Resource.Failure -> {
                    loaderView.gone()
                }
                is Resource.Loading -> {
                    loaderView.visible()
                }
                is Resource.Success -> {
                    loaderView.gone()
                    LogExt.e(it.data.toString())
                    startActivity(Intent(mContext, MainActivity::class.java))
                }
            }
        }
    }

}
