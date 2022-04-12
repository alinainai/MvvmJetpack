package com.gas.container

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment

fun FragmentContainerActivity.startActivity(context: Activity, fragmentClazz: Class<out Fragment>) {
    startActivity(context, fragmentClazz, null)
}

fun FragmentContainerActivity.startActivity(context: Activity, fragmentClazz: Class<out Fragment>, args: Bundle?, vararg intentFlags: Int) {
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

fun FragmentContainerActivity.startActivityForResult(context: Activity, fragmentClazz: Class<out Fragment?>?, requestCode: Int) {
    startActivityForResult(context, fragmentClazz, null, requestCode)
}

fun FragmentContainerActivity.startActivityForResult(context: Activity, fragmentClazz: Class<out Fragment?>?, args: Bundle?, requestCode: Int) {
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
fun FragmentContainerActivity.startSceneTrans(activity: Activity, fragmentClazz: Class<out Fragment?>?, args: Bundle?, view: View) {
    val intent = Intent(activity, FragmentContainerActivity::class.java)
    intent.putExtra("clazz", fragmentClazz)
    if (args != null) {
        intent.putExtras(args)
    }
    val campat = ActivityOptionsCompat.makeScaleUpAnimation(view, view.width / 2, view.height / 2, 0, 0)
    ActivityCompat.startActivity(activity, intent, campat.toBundle())
    activity.overridePendingTransition(0, 0)
}
