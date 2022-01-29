package com.mihua.ui.login

import android.view.View
import com.gas.base.activity.BaseVMActivity
import com.mihua.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseVMActivity() {

    override fun layoutId()= R.layout.activity_login
    override fun initData() {
        findViewById<View>(R.id.login).setOnClickListener {

        }
    }
}
