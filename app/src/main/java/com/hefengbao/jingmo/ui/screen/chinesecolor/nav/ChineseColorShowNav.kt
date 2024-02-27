package com.hefengbao.jingmo.ui.screen.chinesecolor.nav

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.chinesecolor.ChineseColorShowRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

@VisibleForTesting
internal const val chineseColorIdArg = "chineseColorId"
private const val base = "chinese_color_show"
private const val ROUTE = "$base/{$chineseColorIdArg}"

internal class ChineseColorArgs(val chineseColorId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(URLDecoder.decode(checkNotNull(savedStateHandle[chineseColorIdArg]), UTF_8.name()))
}

fun NavController.navigateToChineseColorShowScreen(id: String) {
    val encodedId = URLEncoder.encode(id, UTF_8.name())
    this.navigate("$base/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.chineseColorShowScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(chineseColorIdArg) { type = NavType.StringType }
        )
    ) {
        ChineseColorShowRoute(
            onBackClick = onBackClick
        )
    }
}