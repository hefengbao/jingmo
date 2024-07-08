/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.link.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.link.LinkIndexRoute

private const val ROUTE = "link_index"

fun NavController.navigateToLinkIndexScreen() {
    this.navigate(ROUTE)
}

fun NavGraphBuilder.linkIndexScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = ROUTE
    ) {
        LinkIndexRoute(
            onBackClick = onBackClick
        )
    }
}