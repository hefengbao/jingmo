package com.hefengbao.jingmo.ui.screen.chineseexpression.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.chineseexpression.ChineseExpressionIndexRoute

private const val ROUTE = "chinese_expression_index"
private const val ROUTE_GRAPH = "chinese_expression_graph"

fun NavController.navigateToChineseExpressionGraph() {
    this.navigate(ROUTE_GRAPH) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.chineseExpressionGraph(
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit,
) {
    navigation(
        startDestination = ROUTE,
        route = ROUTE_GRAPH
    ) {
        composable(ROUTE) {
            ChineseExpressionIndexRoute(onBackClick = onBackClick, onSearchClick = onSearchClick)
        }

        nestGraph()
    }
}