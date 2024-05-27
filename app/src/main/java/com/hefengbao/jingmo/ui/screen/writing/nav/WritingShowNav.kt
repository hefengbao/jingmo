package com.hefengbao.jingmo.ui.screen.writing.nav

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.writing.WritingShowRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

@VisibleForTesting
internal const val writingSearchReadIdArg = "writingId"
internal const val writingSearchReadTypeArg = "type"
internal const val writingSearchReadQueryArg = "query"

private const val base = "writing_search_read"

private const val ROUTE =
    "$base/{$writingSearchReadIdArg}/{$writingSearchReadTypeArg}/{$writingSearchReadQueryArg}"

internal class WritingSearchReadArgs(val poemId: String, val type: String, val query: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[writingSearchReadIdArg]),
                    UTF_8.name()
                ),
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[writingSearchReadTypeArg]),
                    UTF_8.name()
                ),
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[writingSearchReadQueryArg]),
                    UTF_8.name()
                ),
            )
}

fun NavController.navigateToWritingSearchShowScreen(id: String, type: String, query: String) {
    val encodedId = URLEncoder.encode(id, UTF_8.name())
    val encodedType = URLEncoder.encode(type, UTF_8.name())
    val encodedQuery = URLEncoder.encode(query, UTF_8.name())
    this.navigate("$base/$encodedId/$encodedType/$encodedQuery") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.writingSearchShowScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(writingSearchReadIdArg) { type = NavType.StringType },
            navArgument(writingSearchReadTypeArg) { type = NavType.StringType },
            navArgument(writingSearchReadQueryArg) { type = NavType.StringType },
        )
    ) {
        WritingShowRoute(
            onBackClick = onBackClick,
            onCaptureClick = onCaptureClick
        )
    }
}