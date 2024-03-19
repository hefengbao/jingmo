package com.hefengbao.jingmo.ui.screen.settings.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.settings.ImportRoute

private const val ROUTE = "settings_import_data"

fun NavController.navigateToImportScreen() {
    this.navigate(ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.importScreen(
    onBackClick: () -> Unit
) {
    composable(ROUTE) {
        ImportRoute(
            onBackClick = onBackClick
        )
    }
}