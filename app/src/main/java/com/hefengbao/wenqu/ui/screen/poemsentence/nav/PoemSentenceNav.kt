package com.hefengbao.wenqu.ui.screen.poemsentence.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.wenqu.ui.screen.poem.PoemRoute

private const val ROUTE_POEM = "poem"
private const val ROUTE_POEM_GRAPH = "poem_graph"

fun NavController.navigateToPoemGraph(){
    this.navigate(ROUTE_POEM_GRAPH)
}

fun NavGraphBuilder.poemGraph(
    onBackClick: () -> Unit,
    nestGraph:NavGraphBuilder.() -> Unit
){
    navigation(
        startDestination = ROUTE_POEM,
        route = ROUTE_POEM_GRAPH
    ){
        composable(ROUTE_POEM){
            PoemRoute(
                onBackClick = onBackClick
            )
        }
    }

    nestGraph()
}