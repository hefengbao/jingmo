package com.hefengbao.jingmo.ui.screen.idiom.nav

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.idiom.IdiomRoute
import java.net.URLDecoder
import java.net.URLEncoder

@VisibleForTesting
internal const val idiomIdArg = "idiomId"
private const val base = "idiom_show"
private const val ROUTE_IDIOM_SHOW = "$base/{$idiomIdArg}"

internal class IdiomShowArgs(val idiomId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[idiomIdArg]),
                    Charsets.UTF_8.name()
                )
            )
}

fun NavController.navigateToIdiomShowScreen(id: String) {
    val encodedId = URLEncoder.encode(id, Charsets.UTF_8.name())
    this.navigate("$base/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.idiomShowScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
) {
    composable(
        route = ROUTE_IDIOM_SHOW,
        arguments = listOf(
            navArgument(idiomIdArg) { type = NavType.StringType }
        )
    ) {
        IdiomRoute(
            onBackClick = onBackClick,
            onCaptureClick = onCaptureClick,
        )
    }
}