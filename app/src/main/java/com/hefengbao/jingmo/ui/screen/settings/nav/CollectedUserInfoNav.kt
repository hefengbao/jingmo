package com.hefengbao.jingmo.ui.screen.settings.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.settings.CollectedUserInfoRoute

private const val ROUTE = "settings_collected_user_info"

fun NavController.navigateToSettingsCollectedUserInfoScreen() {
    this.navigate(ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.settingsCollectedUserInfoScreen(
    onBackClick: () -> Unit
) {
    composable(ROUTE) {
        CollectedUserInfoRoute(
            onBackClick = onBackClick
        )
    }
}