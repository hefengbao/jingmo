package com.hefengbao.jingmo.ui.screen.poem.nav

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.poem.PoemSearchRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

internal const val poemSearchQueryArg = "query"
internal const val poemSearchTypeArg = "type"

private const val base = "poem_search"

private const val ROUTE = "$base/{$poemSearchTypeArg}/{$poemSearchQueryArg}"

internal class PoemSearchArgs(val type: String, val query: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[poemSearchTypeArg]),
                    UTF_8.name()
                ),
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[poemSearchQueryArg]),
                    UTF_8.name()
                ),
            )
}

fun NavController.navigateToPoemSearchScreen(type: String, query: String) {
    val encodedQuery = URLEncoder.encode(query, UTF_8.name())
    val encodedType = URLEncoder.encode(type, UTF_8.name())
    this.navigate("$base/$encodedType/$encodedQuery") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.poemSearchScreen(
    onBackClick: () -> Unit,
    onItemClick: (id: String, type: String, query: String) -> Unit,
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(poemSearchTypeArg) { type = NavType.StringType },
            navArgument(poemSearchQueryArg) { type = NavType.StringType },
        )
    ) {
        PoemSearchRoute(
            onBackClick = onBackClick,
            onItemClick = onItemClick,
        )
    }
}