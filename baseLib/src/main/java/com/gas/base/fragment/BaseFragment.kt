package com.gas.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gas.ext.app.debug

abstract class BaseFragment : Fragment() {

    protected lateinit var mContext: Context
    private var isLoaded: Boolean = false

    /**
     * View 初始化
     */
    abstract fun setLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View

    /**
     * 普通加载
     */
    abstract fun initData(root: View, savedInstanceState: Bundle?)

    /**
     * ViewPager 懒加载数据
     */
    open fun lazyData(){}

    override fun onAttach(context: Context) {
        super.onAttach(context)
        debug("onAttach")
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        debug("onCreateView")
        return setLayout(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        debug("onViewCreated")
        initData(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        if (!isLoaded) {
            lazyData()
            isLoaded = true
        }
    }


}