package com.mihua.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.gas.base.activity.BaseVMActivity
import androidx.fragment.app.Fragment
import com.gas.adapter.MainFragmentAdapter
import com.mihua.R
import com.mihua.ui.home.HomeFragment


class MainActivity : BaseVMActivity() {
    private val mVm: MainViewModel by viewModels()

    private lateinit var mVp: ViewPager2
    private lateinit var btnMain: View
    private lateinit var btnMine: View

    override fun layoutId(savedInstanceState: Bundle?)= R.layout.activity_main

    override fun initData(savedInstanceState: Bundle?) {
        mVp = findViewById(R.id.view_pager2)
        btnMain = findViewById(R.id.button)
        btnMine = findViewById(R.id.button2)
        btnMain.setOnClickListener {

        }
        btnMine.setOnClickListener {

        }
        val mFragments: MutableList<Fragment> = ArrayList()
        mFragments.add(HomeFragment())
        mFragments.add(HomeFragment())
        mFragments.add(HomeFragment())
        mFragments.add(HomeFragment())
        mFragments.add(HomeFragment())
        val mAdapter = MainFragmentAdapter(this, mFragments)
        mVp.offscreenPageLimit = mFragments.size-1
        mVp.adapter = mAdapter
    }
}