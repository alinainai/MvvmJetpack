package com.mihua.ljxbao.ui.login

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
class LoginActivity : BaseVMActivity() {

    companion object {

        private const val INTENT_KEY_IN_PHONE: String = "phone"
        private const val INTENT_KEY_IN_PASSWORD: String = "password"

        fun start(context: Context, phone: String?, password: String?) {
            val intent = Intent(context, LoginActivity::class.java)
            intent.putExtra(INTENT_KEY_IN_PHONE, phone)
            intent.putExtra(INTENT_KEY_IN_PASSWORD, password)
            if (context !is Activity) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }
    }

    private val mVm: LoginViewModel by viewModels()
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var loaderView: View




    /** logo 缩放比例 */
    private val logoScale: Float = 0.8f

    /** 动画时间 */
    private val animTime: Int = 300

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
//                    startActivity(Intent(mContext, MainActivity::class.java))
                    startActivity(Intent(mContext, ComposeActivity::class.java))
                }
            }
        }
    }

}
