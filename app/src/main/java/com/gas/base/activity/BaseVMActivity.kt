package com.gas.base.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.gas.base.BaseViewModel
import com.gas.base.getVmClazz

abstract class BaseVMActivity<VM : BaseViewModel> : AbsBaseActivity<VM>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        mViewModel = ViewModelProvider(this).get(getVmClazz(this))
        initData(savedInstanceState)
    }
}