/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet.nav

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet.AntitheticalCoupletShowRoute
import java.net.URLDecoder
import java.net.URLEncoder

@VisibleForTesting
internal const val showIdArg = "id"
private const val base = "chinese_antithetical_couplet_show"
private const val ROUTE = "$base/{$showIdArg}"

internal class AntitheticalCoupletShowArgs(val id: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[showIdArg]),
                    Charsets.UTF_8.name()
                )
            )
}

fun NavController.navigateToChineseAntitheticalCoupletShowScreen(id: Int) {
    val encodedId = URLEncoder.encode(id.toString(), Charsets.UTF_8.name())
    this.navigate("$base/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.chineseAntitheticalCoupletShowScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(showIdArg) { type = NavType.StringType }
        )
    ) {
        AntitheticalCoupletShowRoute(
            onBackClick = onBackClick,
            onCaptureClick = onCaptureClick,
        )
    }
}