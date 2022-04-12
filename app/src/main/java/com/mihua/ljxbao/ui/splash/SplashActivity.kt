package com.mihua.ljxbao.ui.splash

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import com.gas.base.activity.BaseVMActivity
import com.gas.ext.app.LogExt
import com.gas.ext.app.gone
import com.gas.ext.app.visible
import com.mihua.ljxbao.R
import com.mihua.ljxbao.bean.Resource
import com.mihua.ljxbao.ui.ComposeActivity
import com.mihua.ljxbao.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseVMActivity() {

    private val mVm: SplashViewModel by viewModels()

    override fun layoutId(savedInstanceState: Bundle?) = R.layout.activity_login

    override fun initData(savedInstanceState: Bundle?) {

    }

}
