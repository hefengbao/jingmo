package com.hefengbao.jingmo.ui.screen.poem.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.poem.PoemBookmarksRoute

private const val ROUTE = "poem_bookmarks"

fun NavController.navigateToPoemBookmarksScreen() {
    this.navigate(ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.poemBookmarksScreen(
    onBackClick: () -> Unit,
    onItemClick: (id: Int) -> Unit,
) {
    composable(ROUTE) {
        PoemBookmarksRoute(
            onBackClick = onBackClick,
            onItemClick = onItemClick
        )
    }
}