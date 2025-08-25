/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.character.nav

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.chinese.character.CharacterSearchListRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

internal const val searchTypeArg = "type"
internal const val searchQueryArg = "query"
private const val base = "chinese_character_search_list"
private const val ROUTE =
    "$base/{$searchQueryArg}/{$searchTypeArg}"

internal class CharacterSearchListArgs(val type: String, val query: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(savedStateHandle[searchTypeArg], UTF_8.name()),
                URLDecoder.decode(savedStateHandle[searchQueryArg], UTF_8.name())
            )
}

fun NavController.navigateToChineseCharacterSearchListScreen(type: String, query: String) {
    val encodeQuery = URLEncoder.encode(type, UTF_8.name())
    val encodeType = URLEncoder.encode(query, UTF_8.name())
    this.navigate("$base/$encodeQuery/$encodeType") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.chineseCharacterSearchListScreen(
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(searchQueryArg) { type = NavType.StringType },
            navArgument(searchTypeArg) { type = NavType.StringType },
        )
    ) {
        CharacterSearchListRoute(onBackClick = onBackClick, onItemClick = onItemClick)
    }
}
