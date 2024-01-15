package com.hefengbao.jingmo.ui.screen.idiom.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.idiom.IdiomListRoute

private const val ROUTE_IDIOM_LIST = "idiom_list"
private const val ROUTE_IDIOM_LIST_GRAPH = "idiom_list_graph"

fun NavController.navigateToIdiomListGraph() {
    this.navigate(ROUTE_IDIOM_LIST)
}

fun NavGraphBuilder.idiomListGraph(
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE_IDIOM_LIST,
        route = ROUTE_IDIOM_LIST_GRAPH
    ) {
        composable(ROUTE_IDIOM_LIST) {
            IdiomListRoute(
                onBackClick = onBackClick,
                onItemClick = onItemClick,
            )
        }
    }

    nestGraph()
}