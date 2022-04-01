package com.gas.base.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar

abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var mContext: Context
    /** 状态栏沉浸 */
    private var immersionBar: ImmersionBar? = null

    abstract fun setLayout(savedInstanceState: Bundle?)
    abstract fun initData(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setLayout(savedInstanceState)
        initData(savedInstanceState)
    }
    
    protected open fun createStatusBarConfig(): ImmersionBar =ImmersionBar.with(this) // 默认状态栏字体颜色为黑色
        .statusBarDarkFont(isStatusBarDarkFont()) // 指定导航栏背景颜色
        .navigationBarColor(android.R.color.white) // 状态栏字体和导航栏内容自动变色，必须指定状态栏颜色和导航栏颜色才可以自动变色
        .autoDarkModeEnable(true, 0.2f)

    /**
     * 状态栏字体深色模式
     */
    open fun isStatusBarDarkFont() = true

    /**
     * 获取状态栏沉浸的配置对象
     */
    open fun getStatusBarConfig(): ImmersionBar {
        if (immersionBar == null) {
            immersionBar = createStatusBarConfig()
        }
        return immersionBar!!
    }
}