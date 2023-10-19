package com.hefengbao.jingmo.ui.screen.home.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.home.HomeRoute

const val ROUTE_HOME_GRAPH = "home_graph"
private const val ROUTE_HOME = "home"

fun NavGraphBuilder.homeGraph(
    onSettingsClick: () -> Unit,
    onPoemClick: () -> Unit,
    onPoemSentenceClick: () -> Unit,
    onChineseWisecrackClick: () -> Unit,
    onIdiomClick: () -> Unit,
    onChineseColorClick: () -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit,
) {
    navigation(
        startDestination = ROUTE_HOME,
        route = ROUTE_HOME_GRAPH
    ) {
        composable(
            route = ROUTE_HOME
        ) {
            HomeRoute(
                onSettingsClick = onSettingsClick,
                onPoemClick = onPoemClick,
                onPoemSentenceClick = onPoemSentenceClick,
                onChineseWisecrackClick = onChineseWisecrackClick,
                onChineseColorClick = onChineseColorClick,
                onIdiomClick = onIdiomClick,
            )
        }
        nestGraph()
    }

}