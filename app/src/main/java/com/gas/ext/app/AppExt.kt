package com.gas.ext.app

import android.annotation.SuppressLint
import android.app.*
import android.app.job.JobScheduler
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.hardware.SensorManager
import android.location.LocationManager
import android.media.AudioManager
import android.media.MediaRouter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.BatteryManager
import android.os.Build
import android.os.PowerManager
import android.os.Vibrator
import android.os.storage.StorageManager
import android.telephony.CarrierConfigManager
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.*
import android.view.accessibility.AccessibilityManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.Dimension
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.gas.utils.AppUtils
import com.gas.utils.sp.SPStaticUtils
import java.io.File
import java.util.*

@SuppressLint("StaticFieldLeak")
val app: Context = AppUtils.app.applicationContext

/**
 * copyToClipboard 将文本复制到剪贴板
 */
object App {

    @JvmStatic
    fun copyToClipboard(str: String, context: Context = app, label: String = "copy"): Boolean {
        return try {
            val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val mClipData = ClipData.newPlainText(label, str)
            cm.setPrimaryClip(mClipData)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    @JvmStatic
    fun toast(str: String, ctx: Context = app) {
        Toast.makeText(ctx.applicationContext, str, Toast.LENGTH_SHORT).show()
    }

    //是否安装app
    @JvmStatic
    fun checkAppInstall(pkgName: String): Boolean {
        var packageInfo: PackageInfo? = null
        try {
            pkgName.let {
                packageInfo = app.packageManager.getPackageInfo(it, 0)
            }
        } catch (e: java.lang.Exception) {
            packageInfo = null
            e.printStackTrace()
        }
        return packageInfo != null
    }

    @JvmStatic
    fun getAppVersionName(packageName: String = AppUtils.app.packageName): String? {
        return try {
            val pm = AppUtils.app.packageManager
            val pi = pm.getPackageInfo(packageName, 0)
            pi?.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            ""
        }
    }
    @JvmStatic
    fun getAppVersionCode(packageName: String = AppUtils.app.packageName): Long {
        return try {
            val pm = AppUtils.app.packageManager
            val pi = pm.getPackageInfo(packageName, 0)
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) {
                pi?.versionCode?.toLong() ?: -1
            } else {
                pi?.longVersionCode ?: -1
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            -1
        }
    }
    @JvmStatic
    fun fixSoftInputLeaks(window: Window) {
        val imm = app.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val leakViews = arrayOf("mLastSrvView", "mCurRootView", "mServedView", "mNextServedView")
        for (leakView in leakViews) {
            try {
                val leakViewField = InputMethodManager::class.java.getDeclaredField(leakView)
                if (!leakViewField.isAccessible) {
                    leakViewField.isAccessible = true
                }
                val obj = leakViewField[imm] as? View ?: continue
                if (obj.rootView === window.decorView.rootView) {
                    leakViewField[imm] = null
                }
            } catch (ignore: Throwable) { /**/
            }
        }
    }
    @JvmStatic
    fun getExternalFilesDir(fileName: String? = null): File? {
        return app.getExternalFilesDir(fileName)
    }

    /**
     * 获取唯一标识符
     */
    @JvmStatic
    fun getAppGUID(): String {
        var id = SPStaticUtils.getString("APP_GUID", "")
        if (TextUtils.isEmpty(id)) {
            id = UUID.randomUUID().toString()
            SPStaticUtils.put("APP_GUID", id)
        }
        return id!!
    }
    @JvmStatic
    fun byte2FitMemorySize(byteNum: Long): String {
        return when {
            byteNum < 0 -> {
                "shouldn't be less than zero!"
            }
            byteNum < 1024 -> {
                String.format(Locale.getDefault(), "%.0fB", byteNum.toDouble())
            }
            byteNum < 1048576 -> {
                String.format(Locale.getDefault(), "%.0fKB", byteNum.toDouble() / 1024)
            }
            byteNum < 1073741824 -> {
                String.format(Locale.getDefault(), "%.0fMB", byteNum.toDouble() / 1048576)
            }
            else -> {
                String.format(Locale.getDefault(), "%.0fGB", byteNum.toDouble() / 1073741824)
            }
        }
    }
    @JvmStatic
    @RequiresApi(Build.VERSION_CODES.M)
    fun Context.getWiFiSsid(): String {
        var ssid = "unknown id"
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O || Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val mWifiManager =
                (applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager)
            val info = mWifiManager.connectionInfo
            return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                info.ssid
            } else {
                info.ssid.replace("\"", "")
            }
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O_MR1) {
            val connManager =
                (applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            val networkInfo = connManager.activeNetworkInfo
            if (networkInfo!!.isConnected) {
                if (networkInfo.extraInfo != null) {
                    return networkInfo.extraInfo.replace("\"", "")
                }
            }
        }
        return ssid
    }
    @JvmStatic
    fun hideSoftInput(activity: Activity) {
        var view = activity.currentFocus
        if (view == null) {
            val decorView = activity.window.decorView
            val focusView = decorView.findViewWithTag<View>("keyboardTagView")
            if (focusView == null) {
                view = EditText(activity)
                view.setTag("keyboardTagView")
                (decorView as ViewGroup).addView(view, 1, 1)
            } else {
                view = focusView
            }
            view.requestFocus()
        }
        hideSoftInput(view)
    }

    @JvmStatic
    fun hideSoftInput(view: View) {
        val imm = app.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


}

@JvmOverloads
@Dimension(unit = Dimension.PX)
fun Number.dp2px(metrics: DisplayMetrics = Resources.getSystem().displayMetrics): Float {
    return toFloat() * metrics.density
}

@JvmOverloads
@Dimension(unit = Dimension.DP)
fun Number.px2dp(metrics: DisplayMetrics = Resources.getSystem().displayMetrics): Float {
    return toFloat() / metrics.density
}

inline fun <reified T> Context.getSystemService(): T? =
    ContextCompat.getSystemService(this, T::class.java)

val Context.windowManager get() = getSystemService<WindowManager>()
val Context.clipboardManager get() = getSystemService<ClipboardManager>()
val Context.layoutInflater get() = getSystemService<LayoutInflater>()
val Context.activityManager get() = getSystemService<ActivityManager>()
val Context.powerManager get() = getSystemService<PowerManager>()
val Context.alarmManager get() = getSystemService<AlarmManager>()
val Context.notificationManager get() = getSystemService<NotificationManager>()
val Context.keyguardManager get() = getSystemService<KeyguardManager>()
val Context.locationManager get() = getSystemService<LocationManager>()
val Context.searchManager get() = getSystemService<SearchManager>()
val Context.storageManager get() = getSystemService<StorageManager>()
val Context.vibrator get() = getSystemService<Vibrator>()
val Context.connectivityManager get() = getSystemService<ConnectivityManager>()
val Context.wifiManager get() = getSystemService<WifiManager>()
val Context.audioManager get() = getSystemService<AudioManager>()
val Context.mediaRouter get() = getSystemService<MediaRouter>()
val Context.telephonyManager get() = getSystemService<TelephonyManager>()
val Context.sensorManager get() = getSystemService<SensorManager>()
val Context.subscriptionManager get() = getSystemService<SubscriptionManager>()
val Context.carrierConfigManager get() = getSystemService<CarrierConfigManager>()
val Context.inputMethodManager get() = getSystemService<InputMethodManager>()
val Context.uiModeManager get() = getSystemService<UiModeManager>()
val Context.downloadManager get() = getSystemService<DownloadManager>()
val Context.batteryManager get() = getSystemService<BatteryManager>()
val Context.jobScheduler get() = getSystemService<JobScheduler>()
val Context.accessibilityManager get() = getSystemService<AccessibilityManager>()




