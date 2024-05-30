package com.hefengbao.jingmo.ui.screen.chineseexpression.nav

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.chineseexpression.ChineseExpressionShowRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

@VisibleForTesting
internal const val chineseExpressionShowIdArg = "id"

private const val base = "chinese_expression_show"
private const val ROUTE = "$base/{$chineseExpressionShowIdArg}"

internal class ChineseExpressionShowArgs(val id: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[chineseExpressionShowIdArg]),
                    UTF_8.name()
                )
            )
}

fun NavController.navigateToChineseExpressionShow(id: Int) {
    val encodeId = URLEncoder.encode(id.toString(), UTF_8.name())
    this.navigate("$base/$encodeId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.chineseExpressionShowScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(chineseExpressionShowIdArg) { type = NavType.StringType }
        )
    ) {
        ChineseExpressionShowRoute(onBackClick = onBackClick)
    }
}