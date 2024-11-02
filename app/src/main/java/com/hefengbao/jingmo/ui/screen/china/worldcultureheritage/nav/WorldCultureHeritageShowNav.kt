/*
 *  This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 *  For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.china.worldcultureheritage.nav

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.china.worldcultureheritage.WorldCultureHeritageShowRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

internal const val chinaWorldCultureHeritageShowArgsIdArg = "id"
private const val BASE = "china_world_culture_heritage_show"
private const val ROUTE =
    "china_world_culture_heritage_show/{$chinaWorldCultureHeritageShowArgsIdArg}"

internal class ChinaWorldCultureHeritageShowArgs(val id: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        URLDecoder.decode(savedStateHandle[chinaWorldCultureHeritageShowArgsIdArg], UTF_8.name())
    )
}

fun NavController.navigateToChinaWorldCultureHeritageShowScreen(id: Int) {
    val encodeId = URLEncoder.encode(id.toString(), UTF_8.name())
    this.navigate("$BASE/$encodeId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.chinaWorldCultureHeritageShowScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(chinaWorldCultureHeritageShowArgsIdArg) {
                type = NavType.StringType
            }
        )
    ) {
        WorldCultureHeritageShowRoute(
            onBackClick = onBackClick
        )
    }
}