/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.model

import com.hefengbao.jingmo.BuildConfig
import com.hefengbao.jingmo.data.model.theme.DarkThemeConfig
import com.hefengbao.jingmo.data.model.theme.ThemeBrand

data class AppStatus(
    val captureTextColor: String,
    val captureBackgroundColor: String,
    val themeBrand: ThemeBrand,
    val darkThemeConfig: DarkThemeConfig,
    val useDynamicColor: Boolean,
    val showSyncDataTip: Boolean,
    val userAgreementVersion: Int,
) {
    val showUserAgreementTip = userAgreementVersion < BuildConfig.USER_AGREEMENT_VERSION
}