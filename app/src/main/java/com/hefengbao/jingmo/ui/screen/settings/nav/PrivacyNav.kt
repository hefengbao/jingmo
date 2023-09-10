package com.hefengbao.jingmo.ui.screen.settings.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.settings.PrivacyRoute

private const val ROUTE_PRIVACY = "privacy"

fun NavController.navigateToPrivacyScreen() {
    this.navigate(ROUTE_PRIVACY)
}

fun NavGraphBuilder.privacyScreen(
    onBackClick: () -> Unit
) {
    composable(ROUTE_PRIVACY) {
        PrivacyRoute(onBackClick)
    }
}