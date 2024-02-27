package com.hefengbao.jingmo.ui.screen.idiom.nav

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.idiom.IdiomCaptureRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

@VisibleForTesting
internal const val idiomCaptureIdArg = "idiomId"
private const val base = "idiom_capture"
private const val ROUTE = "$base/{$idiomCaptureIdArg}"

internal class IdiomCaptureArgs(val idiomId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(URLDecoder.decode(checkNotNull(savedStateHandle[idiomCaptureIdArg]), UTF_8.name()))
}

fun NavController.navigateToIdiomCaptureScreen(id: String) {
    val encodedId = URLEncoder.encode(id, UTF_8.name())
    this.navigate("$base/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.idiomCaptureScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(idiomCaptureIdArg) { type = NavType.StringType }
        )
    ) {
        IdiomCaptureRoute(
            onBackClick = onBackClick
        )
    }
}