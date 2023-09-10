package com.hefengbao.jingmo.ui.screen.poemsentence.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.poemsentence.PoemSentenceRoute

private const val ROUTE_POEM_SENTENCE = "poem_sentence"
private const val ROUTE_POEM_SENTENCE_GRAPH = "poem_sentence_graph"

fun NavController.navigateToPoemSentenceGraph() {
    this.navigate(ROUTE_POEM_SENTENCE)
}

fun NavGraphBuilder.poemSentenceGraph(
    onBackClick: () -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE_POEM_SENTENCE,
        route = ROUTE_POEM_SENTENCE_GRAPH
    ) {
        composable(ROUTE_POEM_SENTENCE) {
            PoemSentenceRoute(
                onBackClick = onBackClick
            )
        }
    }

    nestGraph()
}