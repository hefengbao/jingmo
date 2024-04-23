package com.hefengbao.jingmo.ui.screen.home.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.home.HomeRoute

const val ROUTE_HOME_GRAPH = "home_graph"
private const val ROUTE_HOME = "home"

fun NavGraphBuilder.homeGraph(
    onChineseColorClick: () -> Unit,
    onChineseKnowledgeClick: () -> Unit,
    onChineseWisecrackClick: () -> Unit,
    onClassicPoemClick: () -> Unit,
    onFestivalClick: () -> Unit,
    onIdiomClick: () -> Unit,
    onLinksClick: () -> Unit,
    onPeopleClick: () -> Unit,
    onPoemSentenceClick: () -> Unit,
    onRiddleClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onSolarTermsClick: () -> Unit,
    onTongueTwisterClick: () -> Unit,
    onWritingClick: () -> Unit,
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
                onChineseColorClick = onChineseColorClick,
                onChineseKnowledgeClick = onChineseKnowledgeClick,
                onChineseWisecrackClick = onChineseWisecrackClick,
                onClassicPoemClick = onClassicPoemClick,
                onFestivalClick = onFestivalClick,
                onIdiomClick = onIdiomClick,
                onLinksClick = onLinksClick,
                onPeopleClick = onPeopleClick,
                onPoemSentenceClick = onPoemSentenceClick,
                onRiddleClick = onRiddleClick,
                onSettingsClick = onSettingsClick,
                onSolarTermsClick = onSolarTermsClick,
                onTongueTwisterClick = onTongueTwisterClick,
                onWritingClick = onWritingClick
            )
        }
        nestGraph()
    }

}