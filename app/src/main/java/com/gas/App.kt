package com.gas

import android.app.Application
import android.content.Context
import com.gas.utils.AppUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class App : Application(){
    override fun onCreate() {
        super.onCreate()
        AppUtils.init(this)
    }
}
