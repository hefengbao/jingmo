package com.hefengbao.jingmo.ui.screen.poemsentence.nav

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.poem.PoemCaptureRoute
import com.hefengbao.jingmo.ui.screen.poemsentence.PoemSentenceCaptureRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

@VisibleForTesting
internal const val poemSentenceCaptureIdArg = "poemSentenceId"

internal class PoemSentenceCaptureArgs(val poemSentenceId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(URLDecoder.decode(checkNotNull(savedStateHandle[poemSentenceCaptureIdArg]), UTF_8.name()))
}

fun NavController.navigateToPoemSentenceCaptureScreen(id: String) {
    val encodedId = URLEncoder.encode(id, UTF_8.name())
    this.navigate("poem_sentence_capture/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.poemSentenceCaptureScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = "poem_sentence_capture/{$poemSentenceCaptureIdArg}",
        arguments = listOf(
            navArgument(poemSentenceCaptureIdArg) { type = NavType.StringType }
        )
    ) {
        PoemSentenceCaptureRoute(
            onBackClick = onBackClick
        )
    }
}