package com.hefengbao.jingmo.ui.screen.poem.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.poem.PoemIndexRoute

private const val ROUTE_INDEX = "poem_index"
private const val ROUTE_INDEX_GRAPH = "poem_index_graph"

fun NavController.navigateToPoemIndexGraph() {
    this.navigate(ROUTE_INDEX_GRAPH)
}

fun NavGraphBuilder.poemIndexGraph(
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onAuthorClick: (String) -> Unit,
    onCollectClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE_INDEX,
        route = ROUTE_INDEX_GRAPH
    ) {
        composable(
            route = ROUTE_INDEX
        ) {
            PoemIndexRoute(
                onBackClick = onBackClick,
                onSearchClick = onSearchClick,
                onAuthorClick = onAuthorClick,
                onCollectClick = onCollectClick,
                onReadMoreClick = onReadMoreClick,
            )
        }
        nestGraph()
    }
}