package com.gas.base.activity

import android.os.Bundle

abstract class BaseVMActivity : DefaultActivity() {

    abstract fun layoutId(): Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initData()
    }

}