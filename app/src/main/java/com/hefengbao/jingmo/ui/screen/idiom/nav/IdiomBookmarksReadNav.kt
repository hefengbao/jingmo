package com.hefengbao.jingmo.ui.screen.idiom.nav

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.idiom.IdiomBookmarksReadRoute
import java.net.URLDecoder
import java.net.URLEncoder

@VisibleForTesting
internal const val idiomBookmarksReadIdArg = "idiomId"
private const val base = "idiom_bookmarks_read"
private const val ROUTE = "$base/{$idiomBookmarksReadIdArg}"

internal class IdiomBookmarksReadArgs(val idiomId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[idiomBookmarksReadIdArg]),
                    Charsets.UTF_8.name()
                ),
            )
}

fun NavController.navigateToIdiomBookmarksReadScreen(id: String) {
    val encodedId = URLEncoder.encode(id, Charsets.UTF_8.name())
    this.navigate("$base/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.idiomBookmarksReadScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(name = idiomBookmarksReadIdArg) { type = NavType.StringType }
        )
    ) {
        IdiomBookmarksReadRoute(
            onBackClick = onBackClick
        )
    }
}