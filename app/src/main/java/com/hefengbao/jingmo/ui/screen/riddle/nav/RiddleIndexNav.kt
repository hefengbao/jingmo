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
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.riddle.RiddleIndexRoute

private const val ROUTE = "riddle_index"
private const val ROUTE_GRAPH = "riddle_index_graph"

fun NavController.navigateToRiddleIndexGraph() {
    this.navigate(ROUTE_GRAPH)
}

fun NavGraphBuilder.riddleIndexGraph(
    onBackClick: () -> Unit,
    onInfoClick: () -> Unit,
    onSearchClick: () -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE,
        route = ROUTE_GRAPH
    ) {
        composable(
            route = ROUTE
        ) {
            RiddleIndexRoute(
                onBackClick = onBackClick,
                onInfoClick = onInfoClick,
                onSearchClick = onSearchClick
            )
        }
        nestGraph()
    }
}