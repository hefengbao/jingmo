package com.hefengbao.jingmo.ui.screen.classicpoem.nav

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.classicpoem.ClassicPoemShowRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

@VisibleForTesting
internal const val classicPoemShowIdArg = "poemId"
private const val BASE = "classic_poem_show"
private const val ROUTE = "$BASE/{$classicPoemShowIdArg}"

internal class ClassicPoemShowArgs(val poemId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[classicPoemShowIdArg]),
                    UTF_8.name()
                )
            )
}

fun NavController.navigateToClassicPoemShowScreen(id: Int) {
    val encodedId = URLEncoder.encode(id.toString(), UTF_8.name())
    this.navigate("$BASE/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.classicPoemShowScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(classicPoemShowIdArg) { type = NavType.StringType }
        )
    ) {
        ClassicPoemShowRoute(
            onBackClick = onBackClick
        )
    }
}