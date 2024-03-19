package com.hefengbao.jingmo.ui.screen.settings.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.settings.SettingsRoute

private const val ROUTE_SETTINGS = "settings"
private const val ROUTE_SETTINGS_GRAPH = "settings_graph"

fun NavController.navigateToSettingsGraph() {
    this.navigate(ROUTE_SETTINGS_GRAPH)
}

fun NavGraphBuilder.settingsGraph(
    onBackClick: () -> Unit,
    onAboutClick: () -> Unit,
    onPrivacyClick: () -> Unit,
    onDataClick: () -> Unit,
    onImportClick: () -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE_SETTINGS,
        route = ROUTE_SETTINGS_GRAPH
    ) {
        composable(ROUTE_SETTINGS) {
            SettingsRoute(
                onBackClick = onBackClick,
                onAboutClick = onAboutClick,
                onPrivacyClick = onPrivacyClick,
                onDataClick = onDataClick,
                onImportClick = onImportClick
            )
        }
        nestGraph()
    }
}