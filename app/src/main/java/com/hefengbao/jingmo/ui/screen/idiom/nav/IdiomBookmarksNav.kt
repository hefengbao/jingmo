package com.hefengbao.jingmo.ui.screen.idiom.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.idiom.IdiomBookmarksRoute

private const val ROUTE = "idiom_bookmarks"

fun NavController.navigateToIdiomBookmarksScreen() {
    this.navigate(ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.idiomBookmarksScreen(
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    composable(ROUTE) {
        IdiomBookmarksRoute(onBackClick = onBackClick, onItemClick = onItemClick)
    }
}