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

internal class IdiomArgs(val idiomId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[idiomIdArg]),
                    Charsets.UTF_8.name()
                )
            )
}

fun NavController.navigateToIdiomScreen(id: String) {
    val encodedId = URLEncoder.encode(id, Charsets.UTF_8.name())
    this.navigate("idiom/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.idiomScreen(
    onBackClick: () -> Unit,
) {
    composable(
        route = "idiom/{$idiomIdArg}",
        arguments = listOf(
            navArgument(idiomIdArg) { type = NavType.StringType }
        )
    ) {
        IdiomRoute(
            onBackClick = onBackClick
        )
    }
}