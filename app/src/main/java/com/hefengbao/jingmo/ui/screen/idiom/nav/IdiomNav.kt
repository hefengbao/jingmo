package com.hefengbao.jingmo.ui.screen.idiom.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.idiom.IdiomRoute

private const val ROUTE_IDIOM = "idiom"
private const val ROUTE_IDIOM_GRAPH = "idiom_graph"

fun NavController.navigateToIdiomGraph() {
    this.navigate(ROUTE_IDIOM)
}

fun NavGraphBuilder.idiomGraph(
    onBackClick: () -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE_IDIOM,
        route = ROUTE_IDIOM_GRAPH
    ) {
        composable(ROUTE_IDIOM) {
            IdiomRoute(
                onBackClick = onBackClick
            )
        }
    }

    nestGraph()
}