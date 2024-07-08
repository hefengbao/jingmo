/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.poemsentence.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.poemsentence.PoemSentenceIndexRoute

private const val ROUTE = "poem_sentence_index"
private const val ROUTE_GRAPH = "poem_sentence_index_graph"

fun NavController.navigateToPoemSentenceIndexGraph() {
    this.navigate(ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.poemSentenceIndexGraph(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    onSearchClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE,
        route = ROUTE_GRAPH
    ) {
        composable(ROUTE) {
            PoemSentenceIndexRoute(
                onBackClick = onBackClick,
                onCaptureClick = onCaptureClick,
                onSearchItemClick = onSearchClick,
                onBookmarksClick = onBookmarksClick
            )
        }
    }

    nestGraph()
}