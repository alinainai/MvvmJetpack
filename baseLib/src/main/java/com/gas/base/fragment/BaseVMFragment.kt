package com.gas.base.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseVMFragment : BaseFragment() {

    abstract fun layoutId(): Int

    override fun setLayout(inflater: LayoutInflater, container: ViewGroup?): View =
        inflater.inflate(layoutId(), container, false)


}