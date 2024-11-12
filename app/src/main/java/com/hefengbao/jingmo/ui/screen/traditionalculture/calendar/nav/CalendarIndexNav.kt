/*
 *  This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 *  For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.traditionalculture.calendar.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.traditionalculture.calendar.CalendarIndexRoute

private const val ROUTE = "traditional_culture_calendar_index"
private const val ROUTE_GRAPH = "traditional_culture_calendar_graph"

fun NavController.navigateToTraditionalCultureCalendarGraph() {
    this.navigate(ROUTE_GRAPH) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.traditionalCultureCalendarGraph(
    onBackClick: () -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = ROUTE_GRAPH,
        startDestination = ROUTE
    ) {
        composable(ROUTE) {
            CalendarIndexRoute(
                onBackClick = onBackClick
            )
        }
        nestGraph()
    }
}