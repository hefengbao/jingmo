/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.traditionalculture.solarterm.nav

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.traditionalculture.solarterm.SolarTermShowRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

internal const val solarTermShowIdArg = "solarTermId"

private const val base = "traditional_culture_solar_term_show"
private const val ROUTE = "$base/{$solarTermShowIdArg}"

internal class SolarTermShowArgs(val solarTermId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[solarTermShowIdArg]),
                    UTF_8.name()
                )
            )
}

fun NavController.navigateToTraditionalCultureSolarTermShowScreen(id: String) {
    val encodeId = URLEncoder.encode(id, UTF_8.name())
    this.navigate("$base/$encodeId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.traditionalCultureSolarTermShowScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(solarTermShowIdArg) { type = NavType.StringType }
        )
    ) {
        SolarTermShowRoute(onBackClick = onBackClick)
    }
}