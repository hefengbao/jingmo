/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.settings.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.settings.SyncDataRoute

private const val ROUTE_DATA = "settings_sync_data"

fun NavController.navigateToSettingsSyncDataScreen() {
    this.navigate(ROUTE_DATA)
}

fun NavGraphBuilder.settingsSyncDataScreen(
    onBackClick: () -> Unit
) {
    composable(ROUTE_DATA) {
        SyncDataRoute(
            onBackClick = onBackClick
        )
    }
}