package com.hefengbao.jingmo.ui.screen.writing.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.writing.WritingSearchRoute

private const val ROUTE = "writing_search"

fun NavController.navigateToWritingSearchScreen() {
    this.navigate(ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.writingSearchScreen(
    onBackClick: () -> Unit,
    onItemClick: (id: String, type: String, query: String) -> Unit,
) {
    composable(
        route = ROUTE,
    ) {
        WritingSearchRoute(
            onBackClick = onBackClick,
            onItemClick = onItemClick,
        )
    }
}