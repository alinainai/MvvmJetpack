package com.gas.base.activity

import android.os.Bundle


abstract class BaseVMActivity : BaseActivity() {

    abstract fun layoutId(savedInstanceState: Bundle?): Int

    override fun setContent(savedInstanceState: Bundle?) {
        setContentView(layoutId(savedInstanceState))
    }

}