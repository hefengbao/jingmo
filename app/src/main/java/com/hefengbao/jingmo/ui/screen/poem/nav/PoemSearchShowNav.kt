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

@VisibleForTesting
internal const val poemSearchShowQueryArg = "query"

internal class PoemSearchShowArgs(val poemId: String, val queryString: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[poemSearchShowIdArg]),
                    UTF_8.name()
                ),
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[poemSearchShowQueryArg]),
                    UTF_8.name()
                )
            )
}

fun NavController.navigateToPoemSearchShowScreen(id: String, queryString: String) {
    val encodedId = URLEncoder.encode(id, UTF_8.name())
    val encodedQueryString = URLEncoder.encode(queryString, UTF_8.name())
    this.navigate("poem_search_show/$encodedId/$encodedQueryString") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.poemSearchShowScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Long) -> Unit,
) {
    composable(
        route = "poem_search_show/{$poemSearchShowIdArg}/{$poemSearchShowQueryArg}",
        arguments = listOf(
            navArgument(poemSearchShowIdArg) { type = NavType.StringType },
            navArgument(poemSearchShowQueryArg) { type = NavType.StringType },
        )
    ) {
        PoemSearchShowRoute(
            onBackClick = onBackClick,
            onCaptureClick = onCaptureClick
        )
    }
}