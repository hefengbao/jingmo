package com.hefengbao.jingmo.ui.screen.settings.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.settings.HomeItemManagerRoute

private const val ROUTE = "settings_homeitemmanager"

fun NavController.navigateToHomeItemManagerScreen() {
    this.navigate(ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.homeItemManagerScreen(
    onBackClick: () -> Unit
) {
    composable(ROUTE) {
        HomeItemManagerRoute(
            onBackClick = onBackClick
        )
    }
}