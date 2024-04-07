package com.hefengbao.jingmo.ui.screen.writing.nav

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.writing.WritingSearchRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

internal const val writingSearchQueryArg = "query"
internal const val writingSearchTypeArg = "type"

private const val base = "writing_search"

private const val ROUTE = "$base/{$writingSearchTypeArg}/{$writingSearchQueryArg}"

internal class WritingSearchArgs(val type: String, val query: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[writingSearchTypeArg]),
                    UTF_8.name()
                ),
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[writingSearchQueryArg]),
                    UTF_8.name()
                ),
            )
}

fun NavController.navigateToWritingSearchScreen(type: String, query: String) {
    val encodedQuery = URLEncoder.encode(query, UTF_8.name())
    val encodedType = URLEncoder.encode(type, UTF_8.name())
    this.navigate("$base/$encodedType/$encodedQuery") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.writingSearchScreen(
    onBackClick: () -> Unit,
    onItemClick: (id: String, type: String, query: String) -> Unit,
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(writingSearchTypeArg) { type = NavType.StringType },
            navArgument(writingSearchQueryArg) { type = NavType.StringType },
        )
    ) {
        WritingSearchRoute(
            onBackClick = onBackClick,
            onItemClick = onItemClick,
        )
    }
}