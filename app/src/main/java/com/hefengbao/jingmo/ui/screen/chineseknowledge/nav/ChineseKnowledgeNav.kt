package com.hefengbao.jingmo.ui.screen.chineseknowledge.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.chineseknowledge.ChineseKnowledgeRoute

private const val ROUTE_CHINESE_KNOWLEDGE_GRAPH = "chinese_knowledge_graph"
private const val ROUTE_CHINESE_KNOWLEDGE = "chinese_knowledge"

fun NavController.navigateToChineseKnowledgeGraph() {
    this.navigate(ROUTE_CHINESE_KNOWLEDGE_GRAPH)
}

fun NavGraphBuilder.chineseKnowledgeGraph(
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE_CHINESE_KNOWLEDGE,
        route = ROUTE_CHINESE_KNOWLEDGE_GRAPH
    ) {
        composable(ROUTE_CHINESE_KNOWLEDGE) {
            ChineseKnowledgeRoute(
                onBackClick = onBackClick,
                onSearchClick = onSearchClick
            )
        }
        nestGraph()
    }
}