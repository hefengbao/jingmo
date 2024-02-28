package com.hefengbao.jingmo.ui.screen.poem.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.poem.PoemIndexRoute

private const val ROUTE = "poem_index"
private const val ROUTE_GRAPH = "poem_index_graph"

fun NavController.navigateToPoemIndexGraph() {
    this.navigate(ROUTE_GRAPH){
        launchSingleTop = true
    }
}

fun NavGraphBuilder.poemIndexGraph(
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
            PoemIndexRoute(
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