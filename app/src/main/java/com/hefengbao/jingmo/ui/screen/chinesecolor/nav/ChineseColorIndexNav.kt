package com.hefengbao.jingmo.ui.screen.chinesecolor.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.chinesecolor.ChineseColorIndexRoute

private const val ROUTE = "chinese_color_index"
private const val ROUTE_GRAPH = "chinese_color_index_graph"

fun NavController.navigateToChineseColorIndexGraph() {
    this.navigate(ROUTE_GRAPH)
}

fun NavGraphBuilder.chineseColorIndexGraph(
    onBackClick: () -> Unit,
    onItemClick: (String) -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE,
        route = ROUTE_GRAPH
    ) {
        composable(ROUTE) {
            ChineseColorIndexRoute(
                onBackClick = onBackClick,
                onItemClick = onItemClick
            )
        }
    }

    nestGraph()
}