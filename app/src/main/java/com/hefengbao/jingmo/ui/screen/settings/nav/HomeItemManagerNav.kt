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
import com.hefengbao.jingmo.ui.screen.settings.HomeItemManagerRoute

private const val ROUTE = "settings_home_item_manager"

fun NavController.navigateToSettingsHomeItemManagerScreen() {
    this.navigate(ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.settingsHomeItemManagerScreen(
    onBackClick: () -> Unit
) {
    composable(ROUTE) {
        HomeItemManagerRoute(
            onBackClick = onBackClick
        )
    }
}