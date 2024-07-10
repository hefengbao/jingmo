/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.traditionalculture.color.nav

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.traditionalculture.color.ColorShowRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

@VisibleForTesting
internal const val chineseColorIdArg = "chineseColorId"
private const val base = "traditional_culture_color_show"
private const val ROUTE = "$base/{$chineseColorIdArg}"

internal class ChineseColorArgs(val chineseColorId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(URLDecoder.decode(checkNotNull(savedStateHandle[chineseColorIdArg]), UTF_8.name()))
}

fun NavController.navigateToTraditionalCultureColorShowScreen(id: String) {
    val encodedId = URLEncoder.encode(id, UTF_8.name())
    this.navigate("$base/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.traditionalCultureColorShowScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(chineseColorIdArg) { type = NavType.StringType }
        )
    ) {
        ColorShowRoute(
            onBackClick = onBackClick
        )
    }
}