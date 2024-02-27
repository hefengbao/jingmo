package com.hefengbao.jingmo.ui.screen.poem.nav

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.poem.PoemSearchReadRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

@VisibleForTesting
internal const val poemSearchReadIdArg = "poemId"
internal const val poemSearchReadTypeArg = "type"
internal const val poemSearchReadQueryArg = "query"

private const val base = "poem_search_read"

private const val ROUTE =
    "$base/{$poemSearchReadIdArg}/{$poemSearchReadTypeArg}/{$poemSearchReadQueryArg}"

internal class PoemSearchReadArgs(val poemId: String, val type: String, val query: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[poemSearchReadIdArg]),
                    UTF_8.name()
                ),
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[poemSearchReadTypeArg]),
                    UTF_8.name()
                ),
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[poemSearchReadQueryArg]),
                    UTF_8.name()
                ),
            )
}

fun NavController.navigateToPoemSearchShowScreen(id: String, type: String, query: String) {
    val encodedId = URLEncoder.encode(id, UTF_8.name())
    val encodedType = URLEncoder.encode(type, UTF_8.name())
    val encodedQuery = URLEncoder.encode(query, UTF_8.name())
    this.navigate("$base/$encodedId/$encodedType/$encodedQuery") {
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
            navArgument(poemSearchReadIdArg) { type = NavType.StringType },
            navArgument(poemSearchReadTypeArg) { type = NavType.StringType },
            navArgument(poemSearchReadQueryArg) { type = NavType.StringType },
        )
    ) {
        PoemSearchReadRoute(
            onBackClick = onBackClick,
            onCaptureClick = onCaptureClick
        )
    }
}