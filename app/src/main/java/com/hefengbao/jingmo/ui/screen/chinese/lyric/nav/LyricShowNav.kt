/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.lyric.nav

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.chinese.lyric.LyricShowRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

internal const val lyricShowIdArg = "id"
private const val base = "chinese_lyric_show"
private const val ROUTE = "$base/${lyricShowIdArg}"

internal class LyricShowArgs(val id: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        URLDecoder.decode(checkNotNull(savedStateHandle[lyricShowIdArg]), UTF_8.name())
    )
}

fun NavController.navigateToChineseLyricShowScreen(id: String) {
    val encodedId = URLEncoder.encode(id, UTF_8.name())

    this.navigate("$base/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.chineseLyricShowScreen(
    onBackClick: () -> Unit,
) {
    composable(
        route = "$base/{$lyricShowIdArg}",
        arguments = listOf(
            navArgument(lyricShowIdArg) { type = NavType.StringType }
        )
    ) {
        LyricShowRoute(
            onBackClick = onBackClick
        )
    }
}