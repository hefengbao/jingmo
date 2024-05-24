package com.hefengbao.jingmo.ui.screen.classicpoem.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.classicpoem.ClassicPoemIndexRoute

private const val ROUTE = "classic_poem_index"
private const val ROUTE_GRAPH = "classic_poem_index_graph"

fun NavController.navigateToClassicPoemIndexGraph() {
    this.navigate(ROUTE_GRAPH) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.classicPoemIndexGraph(
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    onSearchClick: () -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE,
        route = ROUTE_GRAPH
    ) {
        composable(route = ROUTE) {
            ClassicPoemIndexRoute(
                onBackClick = onBackClick,
                onBookmarksClick = onBookmarksClick,
                onReadMoreClick = onReadMoreClick,
                onSearchClick = onSearchClick
            )
        }
        nestGraph()
    }
}