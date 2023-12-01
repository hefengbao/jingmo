package com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.ChineseWisecrackSearchShowRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

internal const val chineseWisecrackSearchShowIdArg = "id"
internal const val chineseWisecrackSearchShowQueryArg = "query"

internal class ChineseWisecrackSearchShowArgs(val id: String, val query: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[chineseWisecrackSearchShowIdArg]),
                    UTF_8.name()
                ),
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[chineseWisecrackSearchShowQueryArg]),
                    UTF_8.name()
                )
            )
}


fun NavController.navigateToChineseWisecrackSearchShowScreen(id: String, query: String) {
    val encodeId = URLEncoder.encode(id, UTF_8.name())
    val encodeQuery = URLDecoder.decode(query, UTF_8.name())
    this.navigate("chinese_wisecrack_search_show/$encodeId/$encodeQuery")
}

fun NavGraphBuilder.chineseWisecrackSearchShowScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Long) -> Unit,
) {
    composable(
        route = "chinese_wisecrack_search_show/{$chineseWisecrackSearchShowIdArg}/{$chineseWisecrackSearchShowQueryArg}",
        arguments = listOf(
            navArgument(chineseWisecrackSearchShowIdArg) { type = NavType.StringType },
            navArgument(chineseWisecrackSearchShowQueryArg) { type = NavType.StringType },
        )
    ) {
        ChineseWisecrackSearchShowRoute(
            onBackClick = onBackClick,
            onCaptureClick = onCaptureClick
        )
    }
}