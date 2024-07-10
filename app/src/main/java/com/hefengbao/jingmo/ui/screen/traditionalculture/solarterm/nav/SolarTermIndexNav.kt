/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.traditionalculture.solarterm.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.traditionalculture.solarterm.SolarTermIndexRoute

private const val ROUTE = "traditional_culture_solar_term_index"
private const val ROUTE_GRAPH = "traditional_culture_solar_term_index_graph"

fun NavController.navigateToTraditionalCultureSolarTermIndexGraph() {
    return this.navigate(ROUTE_GRAPH)
}

fun NavGraphBuilder.traditionalCultureSolarTermIndexGraph(
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE,
        route = ROUTE_GRAPH
    ) {
        composable(ROUTE) {
            SolarTermIndexRoute(
                onBackClick = onBackClick,
                onItemClick = onItemClick
            )
        }

        nestGraph()
    }
}