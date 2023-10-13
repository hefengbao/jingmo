package com.hefengbao.jingmo.ui.screen.poem.nav

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.poem.PoemRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

@VisibleForTesting
internal const val poemIdArg = "poemId"

internal class PoemArgs(val poemId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(URLDecoder.decode(checkNotNull(savedStateHandle[poemIdArg]), UTF_8.name()))
}

fun NavController.navigateToPoemScreen(id: String) {
    val encodedId = URLEncoder.encode(id, UTF_8.name())
    this.navigate("poem/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.poemScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = "poem/{$poemIdArg}",
        arguments = listOf(
            navArgument(poemIdArg) { type = NavType.StringType }
        )
    ) {
        PoemRoute(
            onBackClick = onBackClick
        )
    }
}