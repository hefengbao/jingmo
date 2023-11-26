package com.hefengbao.jingmo.ui.screen.solarterm.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.solarterm.SolarTermListRoute

private const val ROUTE_SOLAR_TERM_LIST = "solar_term_list"
private const val ROUTE_SOLAR_TERM_GRAPH = "solar_term_graph"

fun NavController.navigateToSolarTermGraph() {
    return this.navigate(ROUTE_SOLAR_TERM_GRAPH)
}

fun NavGraphBuilder.solarTermGraph(
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE_SOLAR_TERM_LIST,
        route = ROUTE_SOLAR_TERM_GRAPH
    ) {
        composable(ROUTE_SOLAR_TERM_LIST) {
            SolarTermListRoute(
                onBackClick = onBackClick,
                onItemClick = onItemClick
            )
        }

        nestGraph()
    }
}