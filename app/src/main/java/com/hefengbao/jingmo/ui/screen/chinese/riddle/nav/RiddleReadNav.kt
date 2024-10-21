/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.riddle.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.chinese.riddle.RiddleReadRoute

private const val ROUTE = "chinese_riddle_read"

fun NavController.navigateToChineseRiddleReadScreen() {
    this.navigate(ROUTE)
}

fun NavGraphBuilder.chineseRiddleReadScreen(
    onBackClick: () -> Unit,
    onInfoClick: () -> Unit,
) {
    composable(
        route = ROUTE
    ) {
        RiddleReadRoute(
            onBackClick = onBackClick,
            onInfoClick = onInfoClick,
        )
    }
}