/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem.ClassicPoemIndexRoute

private const val ROUTE = "classical_literature_classic_poem_index"
private const val ROUTE_GRAPH = "classical_literature_classic_poem_index_graph"

fun NavController.navigateToClassicalLiteratureClassicPoemIndexGraph() {
    this.navigate(ROUTE_GRAPH) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.classicalLiteratureClassicPoemIndexGraph(
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    onSearchClick: () -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE,
        route = ROUTE_GRAPH
    ) {
        composable(route = ROUTE) {
            ClassicPoemIndexRoute(
                onBackClick = onBackClick,
                onBookmarksClick = onBookmarksClick,
                onReadMoreClick = onReadMoreClick,
                onSearchClick = onSearchClick
            )
        }
        nestGraph()
    }
}