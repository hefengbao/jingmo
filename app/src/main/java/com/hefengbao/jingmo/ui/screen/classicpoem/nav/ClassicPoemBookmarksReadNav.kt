package com.hefengbao.jingmo.ui.screen.classicpoem.nav

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.classicpoem.ClassicPoemBookmarksReadRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

@VisibleForTesting
internal const val classicPoemBookmarksReadIdArg = "poemId"
private const val BASE = "classic_poem_bookmarks_read"
private const val ROUTE = "$BASE/{$classicPoemBookmarksReadIdArg}"

internal class ClassicPoemBookmarksReadArgs(val poemId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[classicPoemBookmarksReadIdArg]),
                    UTF_8.name()
                )
            )
}

fun NavController.navigateToClassicPoemBookmarksReadScreen(id: Int) {
    val encodedId = URLEncoder.encode(id.toString(), UTF_8.name())
    this.navigate("$BASE/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.classicPoemBookmarksReadScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(classicPoemBookmarksReadIdArg) { type = NavType.StringType }
        )
    ) {
        ClassicPoemBookmarksReadRoute(
            onBackClick = onBackClick
        )
    }
}