package com.hefengbao.jingmo.ui.screen.poem.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.poem.PoemCollectionRoute

private const val ROUTE = "poem_collection"

fun NavController.navigateToPoemCollectionScreen() {
    this.navigate(ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.poemCollectionScreen(
    onBackClick: () -> Unit,
    onItemClick: (id: Int) -> Unit,
) {
    composable(ROUTE) {
        PoemCollectionRoute(
            onBackClick = onBackClick,
            onItemClick = onItemClick
        )
    }
}