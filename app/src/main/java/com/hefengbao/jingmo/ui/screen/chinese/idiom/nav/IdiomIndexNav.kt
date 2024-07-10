/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.idiom.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.chinese.idiom.IdiomIndexRoute

private const val ROUTE_IDIOM_INDEX = "chinese_idiom_index"
private const val ROUTE_IDIOM_INDEX_GRAPH = "chinese_idiom_index_graph"

fun NavController.navigateToChineseIdiomIndexGraph() {
    this.navigate(ROUTE_IDIOM_INDEX)
}

fun NavGraphBuilder.chineseIdiomIndexGraph(
    onBackClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onSearchClick: () -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE_IDIOM_INDEX,
        route = ROUTE_IDIOM_INDEX_GRAPH
    ) {
        composable(ROUTE_IDIOM_INDEX) {
            IdiomIndexRoute(
                onBackClick = onBackClick,
                onReadMoreClick = onReadMoreClick,
                onBookmarksClick = onBookmarksClick,
                onSearchClick = onSearchClick
            )
        }
    }

    nestGraph()
}