/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet.AntitheticalCoupletBookmarksRoute

private const val ROUTE = "chinese_antithetical_couplet_bookmarks"

fun NavController.navigateToChineseAntitheticalCoupletBookmarksScreen() {
    this.navigate(ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.chineseAntitheticalCoupletBookmarksScreen(
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    composable(ROUTE) {
        AntitheticalCoupletBookmarksRoute(onBackClick = onBackClick, onItemClick = onItemClick)
    }
}