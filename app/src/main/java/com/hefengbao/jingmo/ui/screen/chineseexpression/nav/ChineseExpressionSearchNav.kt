package com.hefengbao.jingmo.ui.screen.chineseexpression.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.chineseexpression.ChineseExpressionSearchRoute

private const val ROUTE = "chinese_expression_search"

fun NavController.navigateToChineseExpressionSearchScreen() {
    this.navigate(ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.chineseExpressionSearchScreen(
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    composable(ROUTE) {
        ChineseExpressionSearchRoute(onBackClick = onBackClick, onItemClick = onItemClick)
    }
}