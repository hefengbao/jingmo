package com.hefengbao.jingmo.ui.screen.poem.nav

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.poem.PoemSearchShowRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

@VisibleForTesting
internal const val poemSearchShowIdArg = "poemId"
internal const val poemSearchShowTypeArg = "type"
internal const val poemSearchShowQueryArg = "query"

private const val ROUTE =
    "poem_search_show/{$poemSearchShowIdArg}/{$poemSearchShowTypeArg}/{$poemSearchShowQueryArg}"

internal class PoemSearchShowArgs(val poemId: String, val type: String, val query: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[poemSearchShowIdArg]),
                    UTF_8.name()
                ),
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[poemSearchShowTypeArg]),
                    UTF_8.name()
                ),
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[poemSearchShowQueryArg]),
                    UTF_8.name()
                ),
            )
}

fun NavController.navigateToPoemSearchShowScreen(id: String, type: String, query: String) {
    val encodedId = URLEncoder.encode(id, UTF_8.name())
    val encodedType = URLEncoder.encode(type, UTF_8.name())
    val encodedQuery = URLEncoder.encode(query, UTF_8.name())
    this.navigate("poem_search_show/$encodedId/$encodedType/$encodedQuery") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.poemSearchShowScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(poemSearchShowIdArg) { type = NavType.StringType },
            navArgument(poemSearchShowTypeArg) { type = NavType.StringType },
            navArgument(poemSearchShowQueryArg) { type = NavType.StringType },
        )
    ) {
        PoemSearchShowRoute(
            onBackClick = onBackClick,
            onCaptureClick = onCaptureClick
        )
    }
}