package com.mihua.ljxbao.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.viewModels
import com.gas.base.fragment.BaseVMFragment
import com.gas.ext.app.LogExt
import com.gas.ext.app.gone
import com.gas.ext.app.visible
import com.mihua.ljxbao.R
import com.mihua.ljxbao.bean.Resource
import com.mihua.ljxbao.ui.ComposeActivity
import com.mihua.ljxbao.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseVMFragment() {

    private val mVm: LoginViewModel by viewModels()
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var loaderView: View

    /** logo 缩放比例 */
    private val logoScale: Float = 0.8f

    /** 动画时间 */
    private val animTime: Int = 300


    override fun layoutId(): Int {
       return R.layout.activity_login
    }

    override fun initData(root: View, savedInstanceState: Bundle?) {
        username = root.findViewById(R.id.username)
        password = root.findViewById(R.id.password)
        loaderView = root.findViewById(R.id.loader_view)

        root.findViewById<View>(R.id.login).setOnClickListener {
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
