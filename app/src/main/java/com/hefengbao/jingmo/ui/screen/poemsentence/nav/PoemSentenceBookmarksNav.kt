package com.hefengbao.jingmo.ui.screen.poemsentence.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.poemsentence.PoemSentenceBookmarksRoute

private const val ROUTE = "poem_sentence_bookmarks"

fun NavController.navigateToPoemSentenceBookmarksScreen() {
    this.navigate(ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.poemSentenceBookmarksScreen(
    onBackClick: () -> Unit
) {
    composable(ROUTE) {
        PoemSentenceBookmarksRoute(
            onBackClick = onBackClick
        )
    }
}