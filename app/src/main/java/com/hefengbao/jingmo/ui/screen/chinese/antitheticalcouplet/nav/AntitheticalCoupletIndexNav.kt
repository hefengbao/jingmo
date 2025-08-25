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
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet.AntitheticalCoupletIndexRoute

private const val ROUTE = "chinese_antithetical_couplet_index"
private const val ROUTE_GRAPH = "chinese_antithetical_couplet_index_graph"

fun NavController.navigateToChineseAntitheticalCoupletIndexGraph() {
    this.navigate(ROUTE)
}

fun NavGraphBuilder.chineseAntitheticalCoupletIndexGraph(
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    onReadMoreClick: () -> Unit,
    onSearchClick: () -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE,
        route = ROUTE_GRAPH
    ) {
        composable(ROUTE) {
            AntitheticalCoupletIndexRoute(
                onBackClick = onBackClick,
                onBookmarksClick = onBookmarksClick,
                onCaptureClick = onCaptureClick,
                onReadMoreClick = onReadMoreClick,
                onSearchClick = onSearchClick
            )
        }
    }

    nestGraph()
}