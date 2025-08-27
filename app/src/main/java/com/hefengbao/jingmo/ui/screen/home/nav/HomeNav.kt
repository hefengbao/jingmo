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
    onBookmarkClick: () -> Unit,
    onChinaWorldCultureHeritageClick: () -> Unit,
    onChineseAntitheticalCoupletClick: () -> Unit,
    onChineseCharacterClick: () -> Unit,
    onChineseExpressionClick: () -> Unit,
    onChineseIdiomClick: () -> Unit,
    onChineseKnowledgeClick: () -> Unit,
    onChineseLyricClick: () -> Unit,
    onChineseModernPoetryClick: () -> Unit,
    onChineseProverbClick: () -> Unit,
    onChineseQuoteClick: () -> Unit,
    onChineseRiddleClick: () -> Unit,
    onChineseTongueTwisterClick: () -> Unit,
    onChineseWisecrackClick: () -> Unit,
    onClassicalLiteratureClassicPoemClick: () -> Unit,
    onClassicalLiteraturePeopleClick: () -> Unit,
    onClassicalLiteratureSentenceClick: () -> Unit,
    onClassicalLiteratureWritingClick: () -> Unit,
    onTraditionalCultureCalendarClick: () -> Unit,
    onTraditionalCultureColorClick: () -> Unit,
    onTraditionalCultureFestivalClick: () -> Unit,
    onTraditionalCultureSolarTermsClick: () -> Unit,
    onLinksClick: () -> Unit,
    onSettingsClick: () -> Unit,
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
                onBookmarkClick = onBookmarkClick,
                onChinaWorldCultureHeritageClick = onChinaWorldCultureHeritageClick,
                onChineseAntitheticalCoupletClick = onChineseAntitheticalCoupletClick,
                onChineseCharacterClick = onChineseCharacterClick,
                onChineseExpressionClick = onChineseExpressionClick,
                onChineseIdiomClick = onChineseIdiomClick,
                onChineseKnowledgeClick = onChineseKnowledgeClick,
                onChineseLyricClick = onChineseLyricClick,
                onChineseModernPoetryClick = onChineseModernPoetryClick,
                onChineseProverbClick = onChineseProverbClick,
                onChineseQuoteClick = onChineseQuoteClick,
                onChineseRiddleClick = onChineseRiddleClick,
                onChineseTongueTwisterClick = onChineseTongueTwisterClick,
                onChineseWisecrackClick = onChineseWisecrackClick,
                onClassicalLiteratureClassicPoemClick = onClassicalLiteratureClassicPoemClick,
                onClassicalLiteraturePeopleClick = onClassicalLiteraturePeopleClick,
                onClassicalLiteratureSentenceClick = onClassicalLiteratureSentenceClick,
                onClassicalLiteratureWritingClick = onClassicalLiteratureWritingClick,
                onTraditionalCultureCalendarClick = onTraditionalCultureCalendarClick,
                onTraditionalCultureColorClick = onTraditionalCultureColorClick,
                onTraditionalCultureFestivalClick = onTraditionalCultureFestivalClick,
                onTraditionalCultureSolarTermsClick = onTraditionalCultureSolarTermsClick,
                onLinksClick = onLinksClick,
                onSettingsClick = onSettingsClick,
            )
        }
        nestGraph()
    }
}