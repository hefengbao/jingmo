package com.hefengbao.jingmo.common.util

import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.provider.Settings
import androidx.annotation.ChecksSdkIntAtLeast
import java.util.Locale
import java.util.TimeZone


object SystemUtil {

    fun getDevice(): String {
        return "{\"name\":\"${Build.BRAND}-${Build.MODEL}\",\"type\":\"android-${Build.VERSION.RELEASE}\"}"
    }

    //http://shunji.wang/852.html

    // 手机品牌 Huawei
    fun getDeviceBrand(): String {
        return Build.BRAND
    }

    // 手机型号 HUAWEI M17-TL00
    fun getDeviceModel(): String {
        return Build.MODEL
    }

    /**
     * 获取当前手机设备名
     * 设备统一型号,不是"关于手机"的中设备名
     * @return 设备名
     */
    fun getDeviceName(): String {
        return Build.DEVICE
    }

    /**
     * 获取手机主板名
     *
     * @return  主板名
     */
    fun getDeviceBoard(): String {
        return Build.BOARD
    }

    /**
     * 获取手机厂商名
     * HuaWei
     * @return  手机厂商名
     */
    fun getDeviceManufacturer(): String {
        return Build.MANUFACTURER;
    }

    // 操作系统版本
    fun getSystemVersion(): String {
        return Build.VERSION.RELEASE
    }

    // 获取当前语言
    fun getSystemLanguage(): String {
        return Locale.getDefault().language
    }

    fun getTimeZone(): String {
        return TimeZone.getDefault().displayName
    }

    fun getVersionCode(context: Context): Long {
        val packageName = context.packageName
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.packageManager.getPackageInfo(
                packageName,
                PackageManager.PackageInfoFlags.of(0)
            ).longVersionCode
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            context.packageManager.getPackageInfo(packageName, 0).longVersionCode
        } else {
            context.packageManager.getPackageInfo(packageName, 0).versionCode.toLong()
        }
    }

    fun getVersionName(context: Context): String {
        val packageName = context.packageName
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.packageManager.getPackageInfo(
                packageName,
                PackageManager.PackageInfoFlags.of(0)
            ).versionName
        } else {
            context.packageManager.getPackageInfo(packageName, 0).versionName
        }
    }

    fun getAppName(context: Context): String {
        return context.packageManager.getApplicationLabel(context.applicationInfo).toString()
    }

    /**
     * 1、分区存储
     */
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q)
    fun isTargetMin29(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    //https://ccpat.blog.csdn.net/article/details/106380620
    fun isLocationServiceEnabled(context: Context): Boolean = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.P -> {
            val locationManager =
                context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager.isLocationEnabled
        }

        else -> {
            try {
                Settings.Secure.getInt(
                    context.contentResolver,
                    Settings.Secure.LOCATION_MODE
                ) != Settings.Secure.LOCATION_MODE_OFF
            } catch (e: Settings.SettingNotFoundException) {
                false
            }
        }
    }

    /**
     * app 是否运行在前台
     */
    fun isRunningForeground(context: Context): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningAppProcesses = activityManager.runningAppProcesses ?: return false
        val packageName = context.applicationInfo.packageName
        for (runningAppProcessInfo in runningAppProcesses) {
            if (packageName.equals(
                    runningAppProcessInfo.processName,
                    ignoreCase = true
                ) && runningAppProcessInfo.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND
            ) {
                return true
            }
        }
        return false
    }
}