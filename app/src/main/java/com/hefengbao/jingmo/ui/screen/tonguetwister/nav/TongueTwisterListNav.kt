package com.hefengbao.jingmo.ui.screen.tonguetwister.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.tonguetwister.TongueTwisterListRoute


private const val ROUTE_TONGUE_TWISTER_LIST = "tongue_twister_list"
private const val ROUTE_TONGUE_TWISTER_GRAPH = "tongue_twister_graph"

fun NavController.navigateToTongueTwisterGraph() {
    this.navigate(ROUTE_TONGUE_TWISTER_GRAPH)
}

fun NavGraphBuilder.tongueTwisterGraph(
    onBackClick: () -> Unit,
    onItemsClick: (Int) -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE_TONGUE_TWISTER_LIST,
        route = ROUTE_TONGUE_TWISTER_GRAPH
    ) {
        composable(ROUTE_TONGUE_TWISTER_LIST) {
            TongueTwisterListRoute(
                onBackClick = onBackClick,
                onItemClick = onItemsClick
            )
        }
    }
    nestGraph()
}