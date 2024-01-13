package com.hefengbao.jingmo.ui.screen.idiom.nav

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.idiom.IdiomSearchShowRoute
import java.net.URLDecoder
import java.net.URLEncoder

@VisibleForTesting
internal const val idiomSearchShowIdArg = "idiomId"

@VisibleForTesting
internal const val idiomSearchShowQueryArg = "query"

internal class IdiomSearchShowArgs(val idiomId: String, val query: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[idiomSearchShowIdArg]),
                    Charsets.UTF_8.name()
                ),
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[idiomSearchShowQueryArg]),
                    Charsets.UTF_8.name()
                ),
            )
}

fun NavController.navigateToIdiomSearchShowScreen(id: String, query: String) {
    val encodedId = URLEncoder.encode(id, Charsets.UTF_8.name())
    val encodedQuery = URLEncoder.encode(query, Charsets.UTF_8.name())
    this.navigate("idiom_search_show/$encodedId/$encodedQuery") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.idiomSearchShowScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
) {
    composable(
        route = "idiom_search_show/{$idiomSearchShowIdArg}/{$idiomSearchShowQueryArg}",
        arguments = listOf(
            navArgument(idiomSearchShowIdArg) { type = NavType.StringType },
            navArgument(idiomSearchShowQueryArg) { type = NavType.StringType },
        )
    ) {
        IdiomSearchShowRoute(
            onBackClick = onBackClick,
            onCaptureClick = onCaptureClick,
        )
    }
}