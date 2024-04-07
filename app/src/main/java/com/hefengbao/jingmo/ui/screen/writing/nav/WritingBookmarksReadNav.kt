package com.hefengbao.jingmo.ui.screen.writing.nav

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.writing.WritingBookmarksReadRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

@VisibleForTesting
internal const val writingBookmarksReadIdArg = "writingId"
private const val base = "writing_bookmarks_read"
private const val ROUTE = "$base/{$writingBookmarksReadIdArg}"

internal class WritingBookmarksReadArgs(val writingId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[writingBookmarksReadIdArg]),
                    UTF_8.name()
                ),
            )
}

fun NavController.navigateToWritingBookmarksReadScreen(id: String) {
    val encodedId = URLEncoder.encode(id, UTF_8.name())
    this.navigate("$base/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.writingBookmarksReadScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(writingBookmarksReadIdArg) { type = NavType.StringType },
        )
    ) {
        WritingBookmarksReadRoute(
            onBackClick = onBackClick,
            onCaptureClick = onCaptureClick
        )
    }
}