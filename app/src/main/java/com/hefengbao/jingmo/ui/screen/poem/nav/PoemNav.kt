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

private const val ROUTE_POEM = "poem/{$poemIdArg}"
private const val ROUTE_POEM_GRAPH = "poem_graph/{$poemIdArg}"

internal class PoemArgs(val poemId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(URLDecoder.decode(checkNotNull(savedStateHandle[poemIdArg]), UTF_8.name()))
}

fun NavController.navigateToPoemGraph(id: String) {
    val encodedId = URLEncoder.encode(id, UTF_8.name())
    this.navigate("poem_graph/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.poemGraph(
    onBackClick: () -> Unit,
    onCaptureClick: (Long) -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE_POEM,
        route = ROUTE_POEM_GRAPH,
    ) {
        composable(
            route = ROUTE_POEM,
            arguments = listOf(
                navArgument(poemIdArg) { type = NavType.StringType }
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