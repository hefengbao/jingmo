/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.home.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.home.HomeRoute

const val ROUTE_HOME_GRAPH = "home_graph"
private const val ROUTE_HOME = "home"

fun NavGraphBuilder.homeGraph(
    onChineseCharacterClick: () -> Unit,
    onChineseColorClick: () -> Unit,
    onChineseExpressionClick: () -> Unit,
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
                onChineseCharacterClick = onChineseCharacterClick,
                onChineseColorClick = onChineseColorClick,
                onChineseExpressionClick = onChineseExpressionClick,
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