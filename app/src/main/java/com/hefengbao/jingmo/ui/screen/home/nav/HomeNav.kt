package com.hefengbao.jingmo.ui.screen.home.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.home.HomeRoute

const val ROUTE_HOME_GRAPH = "home_graph"
private const val ROUTE_HOME = "home"

fun NavGraphBuilder.homeGraph(
    onLinksClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onPoemClick: () -> Unit,
    onPoemSentenceClick: () -> Unit,
    onChineseWisecrackClick: () -> Unit,
    onIdiomClick: () -> Unit,
    onChineseColorClick: () -> Unit,
    onFestivalClick: () -> Unit,
    onSolarTermsClick: () -> Unit,
    onTongueTwisterClick: () -> Unit,
    onChineseKnowledgeClick: () -> Unit,
    onRiddleClick: () -> Unit,
    onPeopleClick: () -> Unit,
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
                onLinksClick = onLinksClick,
                onSettingsClick = onSettingsClick,
                onPoemClick = onPoemClick,
                onPoemSentenceClick = onPoemSentenceClick,
                onChineseWisecrackClick = onChineseWisecrackClick,
                onChineseColorClick = onChineseColorClick,
                onIdiomClick = onIdiomClick,
                onFestivalClick = onFestivalClick,
                onSolarTermsClick = onSolarTermsClick,
                onTongueTwisterClick = onTongueTwisterClick,
                onChineseKnowledgeClick = onChineseKnowledgeClick,
                onRiddleClick = onRiddleClick,
                onPeopleClick = onPeopleClick,
            )
        }
        nestGraph()
    }

}