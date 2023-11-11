package com.hefengbao.jingmo.ui.screen.links.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.links.LinksRoute

private const val LINKS_ROUTE = "links"

fun NavController.navigateToLinksScreen() {
    this.navigate(LINKS_ROUTE)
}

fun NavGraphBuilder.linksScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = LINKS_ROUTE
    ) {
        LinksRoute(
            onBackClick = onBackClick
        )
    }
}