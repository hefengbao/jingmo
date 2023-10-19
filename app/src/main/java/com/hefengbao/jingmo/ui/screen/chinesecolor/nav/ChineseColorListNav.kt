package com.hefengbao.jingmo.ui.screen.chinesecolor.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.chinesecolor.ChineseColorRoute

private const val ROUTE_CHINESE_COLOR_LIST = "chinese_color_list"
private const val ROUTE_CHINESE_COLOR_LIST_GRAPH = "chinese_color_list_graph"

fun NavController.navigateToChineseColorListGraph() {
    this.navigate(ROUTE_CHINESE_COLOR_LIST_GRAPH)
}

fun NavGraphBuilder.chineseColorListGraph(
    onBackClick: () -> Unit,
    onItemClick: (String) -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE_CHINESE_COLOR_LIST,
        route = ROUTE_CHINESE_COLOR_LIST_GRAPH
    ) {
        composable(ROUTE_CHINESE_COLOR_LIST) {
            ChineseColorRoute(
                onBackClick = onBackClick,
                onItemClick = onItemClick
            )
        }
    }

    nestGraph()
}