package com.gas.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.gas.base.BaseViewModel
import com.gas.base.getVmClazz

abstract class BaseVMDBActivity<DB : ViewDataBinding> : BaseComActivity() {

    lateinit var mDataBind: DB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBind = DataBindingUtil.setContentView(this, layoutId())
        mDataBind.lifecycleOwner = this
        initData()
    }
}