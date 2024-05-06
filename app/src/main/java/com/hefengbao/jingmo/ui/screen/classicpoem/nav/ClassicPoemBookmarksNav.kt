package com.hefengbao.jingmo.ui.screen.classicpoem.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.classicpoem.ClassicPoemBookmarksRoute

private const val ROUTE = "classic_poem_bookmarks"
private const val ROUTE_GRAPH = "classic_poem_bookmarks_graph"

fun NavController.navigateToClassicPoemBookmarksGraph() {
    this.navigate(ROUTE_GRAPH) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.classicPoemBookmarksGraph(
    onBackClick: () -> Unit,
    onReadClick: (Int) -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE,
        route = ROUTE_GRAPH
    ) {
        composable(route = ROUTE) {
            ClassicPoemBookmarksRoute(
                onBackClick = onBackClick,
                onReadClick = onReadClick
            )
        }
        nestGraph()
    }
}