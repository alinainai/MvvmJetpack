package com.gas.container

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.gas.base.activity.BaseActivity
import com.gas.baselib.R

class FragmentContainerActivity : BaseActivity() {

    private var mFragment: Fragment? = null

    override fun setLayout(savedInstanceState: Bundle?) {
        getStatusBarConfig().init()
        setContentView(R.layout.activity_fragment_container)
    }

    override fun initData(savedInstanceState: Bundle?) {
        val fragmentClazz = intent.getSerializableExtra("clazz") as Class<out Fragment>
        try {
            mFragment = fragmentClazz.newInstance()
            mFragment!!.arguments = intent.extras
            val beginTransaction = supportFragmentManager
                .beginTransaction()
            beginTransaction.replace(R.id.container, mFragment!!)
                .commitAllowingStateLoss()
        } catch (e: Exception) {
            finish()
        }
    }

}