package com.gas.base.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.gas.base.BaseViewModel
import com.gas.base.getVmClazz

abstract class BaseVMDBActivity<VM : BaseViewModel, DB : ViewDataBinding> : AbsBaseActivity<VM>() {

    lateinit var mDataBind: DB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBind = DataBindingUtil.setContentView(this, layoutId())
        mDataBind.lifecycleOwner = this
        mViewModel = ViewModelProvider(this).get(getVmClazz(this))
        initData(savedInstanceState)
    }
}