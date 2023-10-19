package com.hefengbao.jingmo.ui.screen.chinesecolor.nav

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.chinesecolor.ChineseColorRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

@VisibleForTesting
internal const val chineseColorIdArg = "chineseColorId"

internal class ChineseColorArgs(val chineseColorId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(URLDecoder.decode(checkNotNull(savedStateHandle[chineseColorIdArg]), UTF_8.name()))
}

fun NavController.navigateToChineseColorScreen(id: String) {
    val encodedId = URLEncoder.encode(id, UTF_8.name())
    this.navigate("chinese_color/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.chineseColorScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = "chinese_color/{$chineseColorIdArg}",
        arguments = listOf(
            navArgument(chineseColorIdArg) { type = NavType.StringType }
        )
    ) {
        ChineseColorRoute(
            onBackClick = onBackClick
        )
    }
}