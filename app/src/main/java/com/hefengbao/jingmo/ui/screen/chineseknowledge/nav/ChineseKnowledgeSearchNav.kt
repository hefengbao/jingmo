package com.hefengbao.jingmo.ui.screen.chineseknowledge.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.chineseknowledge.ChineseKnowledgeSearchRoute

private const val ROUTE = "chinese_knowledge_search"

fun NavController.navigateToChineseKnowSearchScreen() {
    this.navigate(ROUTE)
}

fun NavGraphBuilder.chineseKnowledgeSearchScreen(
    onBackClick: () -> Unit
) {
    composable(ROUTE) {
        ChineseKnowledgeSearchRoute(
            onBackClick = onBackClick
        )
    }
}