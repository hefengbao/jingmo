/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.traditionalculture.festival.nav

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.traditionalculture.festival.FestivalRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

internal const val festivalShowIdArg = "festivalId"
private const val base = "traditional_culture_festival_show"
private const val ROUTE = "$base/{$festivalShowIdArg}"

internal class FestivalShowArgs(val festivalId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(URLDecoder.decode(checkNotNull(savedStateHandle[festivalShowIdArg]), UTF_8.name()))
}

fun NavController.navigateToTraditionalCultureFestivalShowScreen(id: Int) {
    val encodedId = URLEncoder.encode(id.toString(), UTF_8.name())
    this.navigate("$base/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.traditionalCultureFestivalShowScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(name = festivalShowIdArg) {
                type = NavType.StringType
            }
        )
    ) {
        FestivalRoute(
            onBackClick = onBackClick
        )
    }
}