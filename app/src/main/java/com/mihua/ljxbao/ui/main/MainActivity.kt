package com.mihua.ljxbao.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.gas.base.activity.BaseVMActivity
import androidx.fragment.app.Fragment
import com.gas.adapter.MainFragmentAdapter
import com.mihua.ljxbao.R
import com.mihua.ljxbao.ui.main.home.HomeFragment
import com.mihua.ljxbao.ui.main.mine.MineFragment
import com.mihua.ljxbao.ui.main.more.MoreFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseVMActivity() {
    private val mVm: MainViewModel by viewModels()

    private lateinit var mVp: ViewPager2
    private lateinit var btnMain: View
    private lateinit var btnMine: View
    private lateinit var btnMore: View

    override fun layoutId(savedInstanceState: Bundle?)= R.layout.activity_main

    override fun initData(savedInstanceState: Bundle?) {
        mVp = findViewById(R.id.view_pager2)
        btnMain = findViewById(R.id.btnHome)
        btnMore = findViewById(R.id.btnMore)
        btnMine = findViewById(R.id.btnMine)
        btnMain.setOnClickListener {
            mVp.setCurrentItem(0,false)
        }
        btnMore.setOnClickListener {
            mVp.setCurrentItem(1,false)
        }
        btnMine.setOnClickListener {
            mVp.setCurrentItem(2,false)
        }
        //禁止滑动
        mVp.isUserInputEnabled = false
        val mFragments: MutableList<Fragment> = ArrayList()
        mFragments.add(HomeFragment())
        mFragments.add(MoreFragment())
        mFragments.add(MineFragment())
        val mAdapter = MainFragmentAdapter(this, mFragments)
        mVp.offscreenPageLimit = mFragments.size-1
        mVp.adapter = mAdapter
    }
}