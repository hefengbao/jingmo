/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.wisecrack.nav

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.chinese.wisecrack.ChineseWisecrackShowRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

internal const val chineseWisecrackShowIdArg = "id"
private const val base = "chinese_wisecrack_show"
private const val ROUTE = "$base/{$chineseWisecrackShowIdArg}"

internal class ChineseWisecrackSearchShowArgs(val id: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[chineseWisecrackShowIdArg]),
                    UTF_8.name()
                )
            )
}


fun NavController.navigateToChineseWisecrackShowScreen(id: String) {
    val encodeId = URLEncoder.encode(id, UTF_8.name())
    this.navigate("$base/$encodeId")
}

fun NavGraphBuilder.chineseWisecrackShowScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(chineseWisecrackShowIdArg) { type = NavType.StringType },
        )
    ) {
        ChineseWisecrackShowRoute(
            onBackClick = onBackClick,
            onCaptureClick = onCaptureClick
        )
    }
}