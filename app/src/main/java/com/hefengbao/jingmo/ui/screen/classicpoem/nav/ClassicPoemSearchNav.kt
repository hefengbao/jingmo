package com.hefengbao.jingmo.ui.screen.classicpoem.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.classicpoem.ClassicPoemSearchRoute

private const val ROUTE = "classic_poem_search"

fun NavController.navigateToClassicPoemSearchScreen() {
    this.navigate(ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.classicPoemSearchScreen(
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    composable(ROUTE) {
        ClassicPoemSearchRoute(onBackClick = onBackClick, onItemClick = onItemClick)
    }
}

