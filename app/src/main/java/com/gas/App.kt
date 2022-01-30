package com.gas

import android.app.Application
import android.content.Context
import com.gas.utils.AppUtils
import com.mihua.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
open class App : Application(){
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        AppUtils.init(this)
    }
}
