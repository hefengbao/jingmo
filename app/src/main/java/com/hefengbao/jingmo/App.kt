/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo

import android.app.Application
import android.content.Context
import com.tencent.bugly.crashreport.CrashReport
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        CrashReport.initCrashReport(applicationContext, BuildConfig.BUGLY_ID, BuildConfig.DEBUG)
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}