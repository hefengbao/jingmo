package com.hefengbao.jingmo.ui.screen.poem.nav

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.poem.PoemCollectionShowRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

@VisibleForTesting
internal const val poemCollectionShowIdArg = "poemId"

private const val ROUTE = "poem_collection_show/{$poemCollectionShowIdArg}"

internal class PoemCollectionShowArgs(val poemId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[poemCollectionShowIdArg]),
                    UTF_8.name()
                ),
            )
}

fun NavController.navigateToPoemCollectionShowScreen(id: String) {
    val encodedId = URLEncoder.encode(id, UTF_8.name())
    this.navigate("poem_collection_show/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.poemCollectionShowScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(poemCollectionShowIdArg) { type = NavType.StringType },
        )
    ) {
        PoemCollectionShowRoute(
            onBackClick = onBackClick,
            onCaptureClick = onCaptureClick
        )
    }
}