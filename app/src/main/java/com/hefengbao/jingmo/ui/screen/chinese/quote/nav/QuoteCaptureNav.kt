package com.hefengbao.jingmo.ui.screen.chinese.quote.nav

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.chinese.quote.QuoteCaptureRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

@VisibleForTesting
internal const val captureIdArg = "id"
private const val base = "chinese_quote_capture"
private const val ROUTE = "$base/{$captureIdArg}"

internal class QuoteCaptureArgs(val id: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(URLDecoder.decode(checkNotNull(savedStateHandle[captureIdArg]), UTF_8.name()))
}

fun NavController.navigateToChineseQuoteCaptureScreen(id: Int) {
    val encodedId = URLEncoder.encode(id.toString(), UTF_8.name())
    this.navigate("$base/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.chineseQuoteCaptureScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(captureIdArg) { type = NavType.StringType }
        )
    ) {
        QuoteCaptureRoute(
            onBackClick = onBackClick
        )
    }
}