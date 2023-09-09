package com.hefengbao.wenqu.ui.screen.chinesewisecrack.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.wenqu.ui.screen.chinesewisecrack.ChineseWisecrackRoute

private const val ROUTE_CHINESE_WISECRACK = "chinese_wisecrack"
private const val ROUTE_CHINESE_WISECRACK_GRAPH = "chinese_wisecrack_graph"

fun NavController.navigateToChineseWisecrackGraph() {
    this.navigate(ROUTE_CHINESE_WISECRACK)
}

fun NavGraphBuilder.chineseWisecrackGraph(
    onBackClick: () -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE_CHINESE_WISECRACK,
        route = ROUTE_CHINESE_WISECRACK_GRAPH
    ) {
        composable(ROUTE_CHINESE_WISECRACK) {
            ChineseWisecrackRoute(
                onBackClick = onBackClick
            )
        }
    }

    nestGraph()
}