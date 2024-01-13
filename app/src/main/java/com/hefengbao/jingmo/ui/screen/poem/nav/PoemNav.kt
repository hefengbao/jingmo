package com.hefengbao.jingmo.ui.screen.poem.nav

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.poem.PoemRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

@VisibleForTesting
internal const val poemIdArg = "poemId"
internal const val poemTypeArg = "type"
internal const val poemQueryArg = "query"

private const val ROUTE_POEM_GRAPH = "poem_graph/{$poemIdArg}/{$poemTypeArg}/{$poemQueryArg}"
private const val ROUTE_POEM = "poem/{$poemIdArg}/{$poemTypeArg}/{$poemQueryArg}"

internal class PoemArgs(val poemId: String, val type: String, val query: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(checkNotNull(savedStateHandle[poemIdArg]), UTF_8.name()),
                URLDecoder.decode(checkNotNull(savedStateHandle[poemTypeArg]), UTF_8.name()),
                URLDecoder.decode(checkNotNull(savedStateHandle[poemQueryArg]), UTF_8.name()),
            )
}

fun NavController.navigateToPoemGraph(id: String, type: String, query: String) {
    val encodedId = URLEncoder.encode(id, UTF_8.name())
    val encodedType = URLEncoder.encode(type, UTF_8.name())
    val encodedQuery = URLEncoder.encode(query, UTF_8.name())
    this.navigate("poem_graph/$encodedId/$encodedType/$encodedQuery") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.poemGraph(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE_POEM,
        route = ROUTE_POEM_GRAPH,
    ) {
        composable(
            route = ROUTE_POEM,
            arguments = listOf(
                navArgument(poemIdArg) { type = NavType.StringType },
                navArgument(poemTypeArg) { type = NavType.StringType },
                navArgument(poemQueryArg) { type = NavType.StringType },
            )
        ) {
            PoemRoute(
                onBackClick = onBackClick,
                onCaptureClick = onCaptureClick
            )
        }
        nestGraph()
    }
}