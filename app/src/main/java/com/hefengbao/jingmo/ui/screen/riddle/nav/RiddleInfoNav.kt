/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.riddle.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.riddle.RiddleInfoRoute

private const val ROUTE = "riddle_info"

fun NavController.navigateToRiddleInfoScreen() {
    this.navigate(ROUTE)
}

fun NavGraphBuilder.riddleInfoScreen(
    onBackClick: () -> Unit,
) {
    composable(ROUTE) {
        RiddleInfoRoute(
            onBackClick = onBackClick
        )
    }
}