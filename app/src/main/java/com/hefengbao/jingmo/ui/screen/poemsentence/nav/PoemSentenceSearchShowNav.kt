package com.hefengbao.jingmo.ui.screen.poemsentence.nav

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.poemsentence.PoemSentenceSearchShowRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

internal const val poemSentenceSearchShowIdArg = "id"
internal const val poemSentenceSearchShowQueryArg = "query"

internal class PoemSentenceSearchShowArgs(val id: String, val query: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[poemSentenceSearchShowIdArg]),
                    UTF_8.name()
                ),
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[poemSentenceSearchShowQueryArg]),
                    UTF_8.name()
                ),
            )
}

fun NavController.navigateToPoemSentenceSearchShowScreen(id: String, query: String) {
    val encodeId = URLEncoder.encode(id, UTF_8.name())
    val encodeQuery = URLEncoder.encode(query, UTF_8.name())
    this.navigate("poemsentence_search_show/$encodeId/$encodeQuery")
}

fun NavGraphBuilder.poemSentenceSearchShowScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Long) -> Unit,
) {
    composable(
        route = "poemsentence_search_show/{$poemSentenceSearchShowIdArg}/{$poemSentenceSearchShowQueryArg}",
        arguments = listOf(
            navArgument(poemSentenceSearchShowIdArg) { type = NavType.StringType },
            navArgument(poemSentenceSearchShowQueryArg) { type = NavType.StringType },
        )
    ) {
        PoemSentenceSearchShowRoute(
            onBackClick = onBackClick,
            onCaptureClick = onCaptureClick
        )
    }
}