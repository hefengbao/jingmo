package com.hefengbao.jingmo.ui.screen.festival.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.festival.FestivalListRoute

private const val ROUTE_FESTIVAL_LIST = "festival_list"
private const val ROUTE_FESTIVAL_GRAPH = "festival_graph"

fun NavController.navigateToFestivalGraph() {
    this.navigate(ROUTE_FESTIVAL_GRAPH)
}

fun NavGraphBuilder.festivalGraph(
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE_FESTIVAL_LIST,
        route = ROUTE_FESTIVAL_GRAPH
    ) {
        composable(ROUTE_FESTIVAL_LIST) {
            FestivalListRoute(onBackClick = onBackClick, onItemClick = onItemClick)
        }

        nestGraph()
    }
}