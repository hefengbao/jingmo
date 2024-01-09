package com.hefengbao.jingmo.ui.screen.settings.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.settings.DataRoute

private const val ROUTE_DATA = "settings_data"

fun NavController.navigateToSettingsDataScreen() {
    this.navigate(ROUTE_DATA)
}

fun NavGraphBuilder.settingsDataScreen(
    onBackClick: () -> Unit
) {
    composable(ROUTE_DATA) {
        DataRoute(
            onBackClick = onBackClick
        )
    }
}