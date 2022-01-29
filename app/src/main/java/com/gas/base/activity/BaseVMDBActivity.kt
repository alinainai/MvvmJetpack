package com.gas.base.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseVMDBActivity<DB : ViewDataBinding> : BaseComActivity() {

    lateinit var mDataBind: DB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBind = DataBindingUtil.setContentView(this, layoutId())
        mDataBind.lifecycleOwner = this
        initData()
    }
}