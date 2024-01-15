package com.hefengbao.jingmo.ui.screen.poemsentence.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.poemsentence.PoemSentenceSearchRoute

private const val ROUTE = "poem_sentence_search"

fun NavController.navigateToPoemSentenceSearchScreen() {
    this.navigate(ROUTE)
}

fun NavGraphBuilder.poemSentenceSearchScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
) {
    composable(
        ROUTE
    ) {
        PoemSentenceSearchRoute(
            onBackClick = onBackClick,
            onCardClick = onCaptureClick
        )
    }
}