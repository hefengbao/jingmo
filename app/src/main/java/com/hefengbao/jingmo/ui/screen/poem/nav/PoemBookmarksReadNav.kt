package com.hefengbao.jingmo.ui.screen.poem.nav

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.poem.PoemBookmarksReadRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

@VisibleForTesting
internal const val poemBookmarksReadIdArg = "poemId"
private const val base = "poem_bookmarks_read"
private const val ROUTE = "$base/{$poemBookmarksReadIdArg}"

internal class PoemBookmarksReadArgs(val poemId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[poemBookmarksReadIdArg]),
                    UTF_8.name()
                ),
            )
}

fun NavController.navigateToPoemBookmarksReadScreen(id: String) {
    val encodedId = URLEncoder.encode(id, UTF_8.name())
    this.navigate("$base/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.poemBookmarksReadScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(poemBookmarksReadIdArg) { type = NavType.StringType },
        )
    ) {
        PoemBookmarksReadRoute(
            onBackClick = onBackClick,
            onCaptureClick = onCaptureClick
        )
    }
}