package com.gas.base.activity

import androidx.appcompat.app.AppCompatActivity

abstract class BaseComActivity: AppCompatActivity() {
    abstract fun layoutId():Int
    abstract fun initData()
}