package com.hefengbao.jingmo.common.ext

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Build
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.data.model.Version
import com.hefengbao.jingmo.data.model.toVersion
import java.io.File

fun Context.restart() {
    packageManager.getLaunchIntentForPackage(packageName)?.let {
        startActivity(Intent.makeRestartActivityTask(it.component))
        Runtime.getRuntime().exit(0)
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

fun Context.getCurrentVersion(): Version = packageManager
    .getPackageInfo(packageName, 0)
    .versionName
    .toVersion()

fun Context.getLatestApk(): File = File(cacheDir, "latest.apk")

fun Context.getFileProvider(): String = "${packageName}.fileprovider"

fun Context.installLatestApk() {
    try {
        val contentUri = FileProvider.getUriForFile(this, getFileProvider(), getLatestApk())
        val intent = Intent(Intent.ACTION_VIEW).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            setDataAndType(contentUri, "application/vnd.android.package-archive")
        }
        if (packageManager.queryIntentActivities(intent, 0).size > 0) {
            startActivity(intent)
        }
    } catch (e: Throwable) {
        e.printStackTrace()
        Log.e("RLog", "installLatestApk: ${e.message}")
    }
}

private var toast: Toast? = null

fun Context.showToast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    toast?.cancel()
    toast = Toast.makeText(this, message, duration)
    toast?.show()
}

fun Context.showToastLong(message: String?) {
    showToast(message, Toast.LENGTH_LONG)
}

fun Context.openURL(
    url: String?,
) {
    if (!url.isNullOrBlank()) {
        val uri = url.trim { it.isWhitespace() || it == '\n' }.toUri()
        val intent = Intent(Intent.ACTION_VIEW, uri)
        val customTabsIntent = CustomTabsIntent.Builder().setShowTitle(true).build()
        try {
            val intents = packageManager.run {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    queryIntentActivities(
                        intent,
                        PackageManager.ResolveInfoFlags.of(PackageManager.MATCH_ALL.toLong())
                    )
                } else {
                    queryIntentActivities(intent, PackageManager.MATCH_ALL)
                }
            }.map {
                Intent(intent).setPackage(it.activityInfo.packageName)
            }.toMutableList()
            val chooser = Intent.createChooser(Intent(), null)
                .putExtra(
                    Intent.EXTRA_ALTERNATE_INTENTS,
                    intents.toTypedArray<Parcelable>()
                )
            startActivity(chooser)
        } catch (_: Throwable) {
            showToast(getString(R.string.open_link_something_wrong))
            startActivity(intent)
        }
    }
}


fun Context.getBrowserAppList(): List<ResolveInfo> {
    val pm: PackageManager = packageManager
    val intent = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_APP_BROWSER)
    val appInfoList = pm.run {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            queryIntentActivities(intent, PackageManager.ResolveInfoFlags.of(0))
        } else {
            queryIntentActivities(intent, 0)
        }
    }
    return appInfoList.sortedBy { it.loadLabel(pm).toString() }
}

fun Context.getDefaultBrowserInfo() = packageManager.run {
    val intent = Intent(Intent.ACTION_VIEW, "https://".toUri())
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        resolveActivity(
            intent,
            PackageManager.ResolveInfoFlags.of(PackageManager.MATCH_DEFAULT_ONLY.toLong())
        )
    } else {
        resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
    }
}

fun Context.getCustomTabsPackages(): List<String> {
    val pm = packageManager
    val intent = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_APP_BROWSER)
    val appInfoList = pm.run {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            queryIntentActivities(intent, PackageManager.MATCH_ALL)
        } else {
            queryIntentActivities(intent, PackageManager.MATCH_ALL)
        }
    }
    return appInfoList.mapNotNull { info ->
        val serviceIntent =
            Intent(ACTION_CUSTOM_TABS_CONNECTION).setPackage(info.activityInfo.packageName)
        val service = pm.run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                resolveService(serviceIntent, PackageManager.ResolveInfoFlags.of(0))
            } else {
                resolveService(serviceIntent, 0)
            }
        }
        if (service != null) {
            return@mapNotNull info.activityInfo.packageName
        }
        return@mapNotNull null
    }.toList()
}
