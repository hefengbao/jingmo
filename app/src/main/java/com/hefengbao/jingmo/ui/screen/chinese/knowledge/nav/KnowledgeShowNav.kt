/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.knowledge.nav

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.chinese.knowledge.KnowledgeShowRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

internal const val showhowIdArg = "id"
private const val base = "chinese_knowledge_show"
private const val ROUTE = "$base/{$showhowIdArg}"

internal class KnowledgeShowArgs(val id: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        URLDecoder.decode(checkNotNull(savedStateHandle[showhowIdArg]), UTF_8.name())
    )
}

fun NavController.navigateToChineseKnowledgeShowScreen(id: Int) {
    val encodedId = URLEncoder.encode(id.toString(), UTF_8.name())

    this.navigate("$base/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.chineseKnowledgeShowScreen(
    onBackClick: () -> Unit,
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(showhowIdArg) { type = NavType.StringType }
        )
    ) {
        KnowledgeShowRoute(
            onBackClick = onBackClick
        )
    }
}