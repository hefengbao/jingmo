package com.hefengbao.jingmo.ui.screen.poemsentence.nav

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.poem.PoemCaptureRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

@VisibleForTesting
internal const val poemCaptureIdArg = "poemId"

internal class PoemCaptureArgs(val poemId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(URLDecoder.decode(checkNotNull(savedStateHandle[poemCaptureIdArg]), UTF_8.name()))
}

fun NavController.navigateToPoemCaptureScreen(id: String) {
    val encodedId = URLEncoder.encode(id, UTF_8.name())
    this.navigate("poem_capture/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.poemCaptureScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = "poem_capture/{$poemCaptureIdArg}",
        arguments = listOf(
            navArgument(poemCaptureIdArg) { type = NavType.StringType }
        )
    ) {
        PoemCaptureRoute(
            onBackClick = onBackClick
        )
    }
}