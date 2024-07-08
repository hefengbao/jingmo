/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.tonguetwister.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.tonguetwister.TongueTwisterIndexRoute


private const val ROUTE = "tongue_twister_index"
private const val ROUTE_GRAPH = "tongue_twister_index_graph"

fun NavController.navigateToTongueTwisterIndexGraph() {
    this.navigate(ROUTE_GRAPH)
}

fun NavGraphBuilder.tongueTwisterIndexGraph(
    onBackClick: () -> Unit,
    onItemsClick: (Int) -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE,
        route = ROUTE_GRAPH
    ) {
        composable(ROUTE) {
            TongueTwisterIndexRoute(
                onBackClick = onBackClick,
                onItemClick = onItemsClick
            )
        }
    }
    nestGraph()
}