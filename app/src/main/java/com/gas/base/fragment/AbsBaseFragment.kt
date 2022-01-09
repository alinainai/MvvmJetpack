package com.gas.base.fragment

import androidx.fragment.app.Fragment
import com.gas.base.BaseViewModel

abstract class AbsBaseFragment<VM:BaseViewModel>:Fragment() {
    lateinit var mViewModel: VM
    abstract fun layoutId(): Int
}