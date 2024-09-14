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
import com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet.AntitheticalCoupletReadRoute

private const val ROUTE = "chinese_antithetical_couplet_read"

fun NavController.navigateToChineseAntitheticalCoupletReadScreen() {
    this.navigate(ROUTE)
}

fun NavGraphBuilder.chineseAntitheticalCoupletReadScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit
) {
    composable(ROUTE) {
        AntitheticalCoupletReadRoute(
            onBackClick = onBackClick,
            onCaptureClick = onCaptureClick
        )
    }
}