package com.gas.base.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseDBFragment<DB : ViewDataBinding> : BaseFragment() {
    lateinit var mDataBind: DB
    abstract fun layoutId(): Int

    override fun setContent(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View {
        mDataBind = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        mDataBind.lifecycleOwner = this
        return mDataBind.root
    }

}