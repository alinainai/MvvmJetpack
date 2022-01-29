package com.gas.base.activity

import android.content.Context
import android.os.Bundle

abstract class BaseVMActivity : BaseComActivity() {

    protected lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setContentView(layoutId())
        initData()
    }

}