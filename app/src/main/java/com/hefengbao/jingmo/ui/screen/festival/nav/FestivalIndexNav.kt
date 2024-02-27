package com.hefengbao.jingmo.ui.screen.festival.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.festival.FestivalIndexRoute

private const val ROUTE = "festival_index"
private const val ROUTE_GRAPH = "festival_index_graph"

fun NavController.navigateToFestivalIndexGraph() {
    this.navigate(ROUTE_GRAPH)
}

fun NavGraphBuilder.festivalIndexGraph(
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE,
        route = ROUTE_GRAPH
    ) {
        composable(ROUTE) {
            FestivalIndexRoute(onBackClick = onBackClick, onItemClick = onItemClick)
        }

        nestGraph()
    }
}