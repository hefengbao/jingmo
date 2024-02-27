package com.hefengbao.jingmo.ui.screen.riddle.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.riddle.RiddleSearchRoute

private const val ROUTE = "riddle_search"

fun NavController.navigateToRiddleSearchScreen() {
    this.navigate(ROUTE)
}

fun NavGraphBuilder.riddleSearchScreen(
    onBackClick: () -> Unit
) {
    composable(ROUTE) {
        RiddleSearchRoute(
            onBackClick = onBackClick
        )
    }
}