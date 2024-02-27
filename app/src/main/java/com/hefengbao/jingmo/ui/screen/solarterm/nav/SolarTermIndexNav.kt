package com.hefengbao.jingmo.ui.screen.solarterm.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.solarterm.SolarTermIndexRoute

private const val ROUTE = "solar_term_index"
private const val ROUTE_GRAPH = "solar_term_index_graph"

fun NavController.navigateToSolarTermIndexGraph() {
    return this.navigate(ROUTE_GRAPH)
}

fun NavGraphBuilder.solarTermIndexGraph(
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