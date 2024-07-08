/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chineseknowledge.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.chineseknowledge.ChineseKnowledgeIndexRoute

private const val ROUTE_GRAPH = "chinese_knowledge_index_graph"
private const val ROUTE = "chinese_knowledge_index"

fun NavController.navigateToChineseKnowledgeIndexGraph() {
    this.navigate(ROUTE_GRAPH)
}

fun NavGraphBuilder.chineseKnowledgeIndexGraph(
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE,
        route = ROUTE_GRAPH
    ) {
        composable(ROUTE) {
            ChineseKnowledgeIndexRoute(
                onBackClick = onBackClick,
                onSearchClick = onSearchClick
            )
        }
        nestGraph()
    }
}