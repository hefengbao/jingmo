package com.hefengbao.jingmo.ui.screen.idiom.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.idiom.IdiomSearchRoute

private const val ROUTE = "idiom_search"

fun NavController.navigateToIdiomSearchScreen() {
    this.navigate(ROUTE)
}

fun NavGraphBuilder.idiomSearchScreen(
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
) {
    composable(ROUTE) {
        IdiomSearchRoute(
            onBackClick = onBackClick,
            onItemClick = onItemClick,
        )
    }
}