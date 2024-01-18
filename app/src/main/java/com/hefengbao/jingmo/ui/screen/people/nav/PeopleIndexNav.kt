package com.hefengbao.jingmo.ui.screen.people.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.people.PeopleIndexRoute

private const val ROUTE = "people_index"
private const val ROUTE_GRAPH = "people_index_graph"

fun NavController.navigateToPeopleGraph() {
    this.navigate(ROUTE)
}

fun NavGraphBuilder.peopleGraph(
    onBackClick: () -> Unit,
    onItemClick: (type: String, query: String) -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE,
        route = ROUTE_GRAPH
    ) {
        composable(ROUTE) {
            PeopleIndexRoute(
                onBackClick = onBackClick,
                onItemClick = onItemClick
            )
        }
        nestGraph()
    }
}