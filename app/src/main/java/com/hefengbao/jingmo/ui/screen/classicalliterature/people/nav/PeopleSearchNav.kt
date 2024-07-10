/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.people.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.classicalliterature.people.PeopleSearchRoute

private const val ROUTE = "classical_literature_people_search"
private const val ROUTE_GRAPH = "classical_literature_people_search_graph"

fun NavController.navigateToClassicalLiteraturePeopleSearchGraph() {
    this.navigate(ROUTE_GRAPH)
}

fun NavGraphBuilder.classicalLiteraturePeopleSearchGraph(
    onBackClick: () -> Unit,
    onItemClick: (String, String) -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE,
        route = ROUTE_GRAPH
    ) {
        composable(route = ROUTE) {
            PeopleSearchRoute(
                onBackClick = onBackClick,
                onItemClick = onItemClick
            )
        }

        nestGraph()
    }
}