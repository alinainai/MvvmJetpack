package com.gas.container

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import com.gas.base.activity.BaseActivity
import com.gas.baselib.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentContainerActivity : BaseActivity() {

    private var mFragment: Fragment? = null

    override fun setLayout(savedInstanceState: Bundle?) {
        getStatusBarConfig().init()
        setContentView(R.layout.activity_fragment_container)
    }

    override fun initData(savedInstanceState: Bundle?) {
        val fragmentClazz = intent.getSerializableExtra("clazz") as Class<out Fragment>
        try {
            mFragment = fragmentClazz.newInstance()
            mFragment!!.arguments = intent.extras
            val beginTransaction = supportFragmentManager
                .beginTransaction()
            beginTransaction.replace(R.id.container, mFragment!!)
                .commitAllowingStateLoss()
        } catch (e: Exception) {
            finish()
        }
    }

    companion object {
        fun startActivity(context: Activity, fragmentClazz: Class<out Fragment>) {
            startActivity(context, fragmentClazz, null)
        }

        fun startActivity(
            context: Activity,
            fragmentClazz: Class<out Fragment>,
            args: Bundle?,
            vararg intentFlags: Int
        ) {
            val intent = Intent(context, FragmentContainerActivity::class.java)
            intent.putExtra("clazz", fragmentClazz)
            if (args != null) {
                intent.putExtras(args)
            }
            if (intentFlags.isNotEmpty()) {
                for (flag in intentFlags) {
                    intent.addFlags(flag)
                }
            }
            context.startActivity(intent)
        }

        fun startActivityForResult(
            context: Activity,
            fragmentClazz: Class<out Fragment?>?,
            requestCode: Int
        ) {
            startActivityForResult(context, fragmentClazz, null, requestCode)
        }

        fun startActivityForResult(
            context: Activity,
            fragmentClazz: Class<out Fragment?>?,
            args: Bundle?,
            requestCode: Int
        ) {
            val intent = Intent(context, FragmentContainerActivity::class.java)
            intent.putExtra("clazz", fragmentClazz)
            if (args != null) {
                intent.putExtras(args)
            }
            context.startActivityForResult(intent, requestCode)
        }

        /**
         * 转场动画启动
         */
        fun startSceneTrans(
            activity: Activity,
            fragmentClazz: Class<out Fragment?>?,
            args: Bundle?,
            view: View
        ) {
            val intent = Intent(activity, FragmentContainerActivity::class.java)
            intent.putExtra("clazz", fragmentClazz)
            if (args != null) {
                intent.putExtras(args)
            }
            val campat = ActivityOptionsCompat.makeScaleUpAnimation(
                view,
                view.width / 2,
                view.height / 2,
                0,
                0
            )
            ActivityCompat.startActivity(activity, intent, campat.toBundle())
            activity.overridePendingTransition(0, 0)
        }
    }

}