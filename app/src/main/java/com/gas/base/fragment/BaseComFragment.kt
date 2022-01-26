package com.gas.base.fragment

import androidx.fragment.app.Fragment

abstract class BaseComFragment: Fragment()  {
    abstract fun layoutId(): Int
    abstract fun initData()
}