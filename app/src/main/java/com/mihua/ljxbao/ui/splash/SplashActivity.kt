package com.mihua.ljxbao.ui.splash

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.gas.base.activity.BaseVMActivity
import com.gas.container.FragmentContainerActivity
import com.mihua.ljxbao.R
import com.mihua.ljxbao.ui.login.LoginFragment
import com.mihua.ljxbao.ui.register.RegisterFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseVMActivity() {

    private val mVm: SplashViewModel by viewModels()

    override fun layoutId(savedInstanceState: Bundle?): Int {
        getStatusBarConfig().init()
        return R.layout.activity_splash
    }

    override fun initData(savedInstanceState: Bundle?) {
        findViewById<View>(R.id.tvSkip).setOnClickListener {
//            startActivity(Intent(this,LoginActivity::class.java))
//            FragmentContainerActivity.startActivity(this, RegisterFragment::class.java)
            FragmentContainerActivity.startActivity(this, LoginFragment::class.java)
        }
    }

}
