package com.hefengbao.jingmo.ui.screen.chineseknowledge.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.chineseknowledge.ChineseKnowledgeIndexRoute

private const val ROUTE_GRAPH = "chinese_knowledge_index_graph"
private const val ROUTE = "chinese_knowledge_index"

fun NavController.navigateToChineseKnowledgeIndexGraph() {
    this.navigate(ROUTE_GRAPH)
}

fun NavGraphBuilder.chineseKnowledgeIndexGraph(
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE,
        route = ROUTE_GRAPH
    ) {
        composable(ROUTE) {
            ChineseKnowledgeIndexRoute(
                onBackClick = onBackClick,
                onSearchClick = onSearchClick
            )
        }
        nestGraph()
    }
}