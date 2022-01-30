package com.gas.base.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class DefaultActivity : AppCompatActivity() {

    protected lateinit var mContext: Context
    abstract fun initData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}