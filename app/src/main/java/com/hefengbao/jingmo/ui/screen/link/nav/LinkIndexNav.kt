package com.hefengbao.jingmo.ui.screen.link.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.link.LinkIndexRoute

private const val ROUTE = "link_index"

fun NavController.navigateToLinkIndexScreen() {
    this.navigate(ROUTE)
}

fun NavGraphBuilder.linkIndexScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = ROUTE
    ) {
        LinkIndexRoute(
            onBackClick = onBackClick
        )
    }
}