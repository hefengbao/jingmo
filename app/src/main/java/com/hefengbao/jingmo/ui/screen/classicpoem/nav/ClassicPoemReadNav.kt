package com.hefengbao.jingmo.ui.screen.classicpoem.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.classicpoem.ClassicPoemReadRoute

private const val ROUTE = "classic_poem_read"

fun NavController.navigateToClassicPoemReadScreen() {
    this.navigate(ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.classicPoemReadScreen(
    onBackClick: () -> Unit
) {
    composable(ROUTE) {
        ClassicPoemReadRoute(
            onBackClick = onBackClick
        )
    }
}