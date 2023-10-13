package com.hefengbao.jingmo.ui.screen.poem.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.poem.PoemListRoute

private const val ROUTE_POEM_LIST = "poem_list"
private const val ROUTE_POEM_LIST_GRAPH = "poem_list_graph"

fun NavController.navigateToPoemListGraph() {
    this.navigate(ROUTE_POEM_LIST_GRAPH)
}

fun NavGraphBuilder.poemListGraph(
    onBackClick: () -> Unit,
    onItemClick: (Long) -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE_POEM_LIST,
        route = ROUTE_POEM_LIST_GRAPH
    ) {
        composable(ROUTE_POEM_LIST) {
            PoemListRoute(
                onBackClick = onBackClick,
                onItemClick = onItemClick
            )
        }
    }

    nestGraph()
}