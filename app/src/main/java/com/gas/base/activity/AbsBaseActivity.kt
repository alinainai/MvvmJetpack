package com.gas.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gas.base.BaseViewModel

abstract class AbsBaseActivity<VM : BaseViewModel> : AppCompatActivity() {
    lateinit var mViewModel: VM
    abstract fun layoutId(): Int
    abstract fun initData(savedInstanceState: Bundle?)
}