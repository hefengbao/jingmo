/*
 *  This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 *  For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.proverb.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.chinese.proverb.ProverbReadRoute

private const val ROUTE = "chinese_proverb_read"

fun NavController.navigateToChineseProverbReadScreen() {
    this.navigate(ROUTE)
}

fun NavGraphBuilder.chineseProverbReadScreen(
    onBackClick: () -> Unit
) {
    composable(ROUTE) {
        ProverbReadRoute(onBackClick = onBackClick)
    }
}