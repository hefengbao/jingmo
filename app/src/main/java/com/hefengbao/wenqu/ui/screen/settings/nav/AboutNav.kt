package com.hefengbao.wenqu.ui.screen.settings.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.wenqu.ui.screen.settings.AboutRoute

private const val ROUTE_ABOUT = "about"

fun NavController.navigateToAboutScreen() {
    this.navigate(ROUTE_ABOUT)
}

fun NavGraphBuilder.aboutScreen(
    onBackClick: () -> Unit
) {
    composable(ROUTE_ABOUT) {
        AboutRoute(onBackClick)
    }
}