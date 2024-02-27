package com.hefengbao.jingmo.ui.screen.riddle.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.riddle.RiddleInfoRoute

private const val ROUTE = "riddle_info"

fun NavController.navigateToRiddleInfoScreen() {
    this.navigate(ROUTE)
}

fun NavGraphBuilder.riddleInfoScreen(
    onBackClick: () -> Unit,
) {
    composable(ROUTE) {
        RiddleInfoRoute(
            onBackClick = onBackClick
        )
    }
}