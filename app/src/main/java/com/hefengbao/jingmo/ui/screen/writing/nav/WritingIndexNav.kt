package com.hefengbao.jingmo.ui.screen.writing.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.writing.WritingIndexRoute

private const val ROUTE = "writing_index"
private const val ROUTE_GRAPH = "writing_index_graph"

fun NavController.navigateToWritingIndexGraph() {
    this.navigate(ROUTE_GRAPH) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.writingIndexGraph(
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onAuthorClick: (String) -> Unit,
    onBookmarksClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE,
        route = ROUTE_GRAPH
    ) {
        composable(
            route = ROUTE
        ) {
            WritingIndexRoute(
                onBackClick = onBackClick,
                onSearchClick = onSearchClick,
                onAuthorClick = onAuthorClick,
                onBookmarksClick = onBookmarksClick,
                onReadMoreClick = onReadMoreClick,
            )
        }
        nestGraph()
    }
}