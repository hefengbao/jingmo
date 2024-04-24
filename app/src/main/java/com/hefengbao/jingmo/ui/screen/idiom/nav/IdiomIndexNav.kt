package com.hefengbao.jingmo.ui.screen.idiom.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.idiom.IdiomIndexRoute

private const val ROUTE_IDIOM_INDEX = "idiom_index"
private const val ROUTE_IDIOM_INDEX_GRAPH = "idiom_index_graph"

fun NavController.navigateToIdiomIndexGraph() {
    this.navigate(ROUTE_IDIOM_INDEX)
}

fun NavGraphBuilder.idiomIndexGraph(
    onBackClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onSearchClick: () -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE_IDIOM_INDEX,
        route = ROUTE_IDIOM_INDEX_GRAPH
    ) {
        composable(ROUTE_IDIOM_INDEX) {
            IdiomIndexRoute(
                onBackClick = onBackClick,
                onReadMoreClick = onReadMoreClick,
                onBookmarksClick = onBookmarksClick,
                onSearchClick = onSearchClick
            )
        }
    }

    nestGraph()
}