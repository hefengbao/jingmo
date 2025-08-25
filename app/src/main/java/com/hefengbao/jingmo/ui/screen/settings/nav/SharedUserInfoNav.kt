package com.hefengbao.jingmo.ui.screen.settings.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.settings.SharedUserInfoRoute

private const val ROUTE = "settings_shared_user_info"

fun NavController.navigateToSettingsSharedUserInfoScreen() {
    this.navigate(ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.settingsSharedUserInfoScreen(
    onBackClick: () -> Unit
) {
    composable(ROUTE) {
        SharedUserInfoRoute(
            onBackClick = onBackClick
        )
    }
}