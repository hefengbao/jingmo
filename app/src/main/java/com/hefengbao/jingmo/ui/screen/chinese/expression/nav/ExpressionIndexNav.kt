/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.expression.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.chinese.expression.ChineseExpressionIndexRoute

private const val ROUTE = "chinese_expression_index"
private const val ROUTE_GRAPH = "chinese_expression_graph"

fun NavController.navigateToChineseExpressionGraph() {
    this.navigate(ROUTE_GRAPH) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.chineseExpressionGraph(
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onSearchClick: () -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit,
) {
    navigation(
        startDestination = ROUTE,
        route = ROUTE_GRAPH
    ) {
        composable(ROUTE) {
            ChineseExpressionIndexRoute(
                onBackClick = onBackClick,
                onBookmarksClick = onBookmarksClick,
                onSearchClick = onSearchClick
            )
        }

        nestGraph()
    }
}