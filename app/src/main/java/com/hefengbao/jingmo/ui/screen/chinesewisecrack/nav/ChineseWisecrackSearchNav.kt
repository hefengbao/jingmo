/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.ChineseWisecrackSearchRoute

private const val ROUTE = "chinese_wisecrack_search"

fun NavController.navigateToChineseWisecrackSearchScreen() {
    this.navigate(ROUTE)
}

fun NavGraphBuilder.chineseWisecrackSearchScreen(
    onBackClick: () -> Unit,
    onItemClick: (id: Int) -> Unit
) {
    composable(ROUTE) {
        ChineseWisecrackSearchRoute(
            onBackClick = onBackClick,
            onItemClick = onItemClick
        )
    }
}