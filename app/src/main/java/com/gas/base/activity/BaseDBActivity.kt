package com.gas.base.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseDBActivity<DB : ViewDataBinding> : BaseActivity() {

    lateinit var mDataBind: DB

    abstract fun layoutId(savedInstanceState: Bundle?): Int

    override fun setContent(savedInstanceState: Bundle?) {
        mDataBind = DataBindingUtil.setContentView(this, layoutId(savedInstanceState))
        mDataBind.lifecycleOwner = this
    }
}