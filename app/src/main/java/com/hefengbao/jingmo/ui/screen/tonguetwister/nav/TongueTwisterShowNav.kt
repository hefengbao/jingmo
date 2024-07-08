/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.tonguetwister.nav

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.tonguetwister.TongueTwisterShowRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

internal const val tongueTwisterShowIdArg = "tongueTwisterId"
private const val base = "tongue_twister"
private const val ROUTE = "$base/{$tongueTwisterShowIdArg}"

internal class TongueTwisterShowArgs(val tongueTwisterId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[tongueTwisterShowIdArg]),
                    UTF_8.name()
                )
            )
}

fun NavController.navigateToTongueTwisterShowScreen(id: String) {
    val encodeId = URLEncoder.encode(id, UTF_8.name())
    this.navigate("$base/$encodeId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.tongueTwisterShowScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(tongueTwisterShowIdArg) { type = NavType.StringType }
        )
    ) {
        TongueTwisterShowRoute(
            onBackClick = onBackClick,
        )
    }
}