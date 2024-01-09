package com.hefengbao.jingmo.ui.screen.riddle.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.riddle.RiddleRoute

private const val ROUTE_RIDDLE = "route_riddle"
private const val ROUTE_RIDDLE_GRAPH = "route_riddle_graph"

fun NavController.navigateToRiddleGraph() {
    this.navigate(ROUTE_RIDDLE_GRAPH)
}

fun NavGraphBuilder.riddleGraph(
    onBackClick: () -> Unit,
    onInfoClick: () -> Unit,
    onSearchClick: () -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE_RIDDLE,
        route = ROUTE_RIDDLE_GRAPH
    ) {
        composable(
            route = ROUTE_RIDDLE
        ) {
            RiddleRoute(
                onBackClick = onBackClick,
                onInfoClick = onInfoClick,
                onSearchClick = onSearchClick
            )
        }
        nestGraph()
    }
}