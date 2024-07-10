/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.people.nav

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.classicalliterature.people.PeopleShowRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

internal const val peopleShowTypeArg = "type"
internal const val peopleShowQueryArg = "id"
private const val base = "classical_literature_people_show"
private const val ROUTE = "$base/{$peopleShowTypeArg}/{$peopleShowQueryArg}"

internal class PeopleShowArgs(val type: String, val query: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(savedStateHandle[peopleShowTypeArg], UTF_8.name()),
                URLDecoder.decode(savedStateHandle[peopleShowQueryArg], UTF_8.name())
            )
}

fun NavController.navigateToClassicalLiteraturePeopleShowScreen(type: String, query: String) {
    val encodeType = URLEncoder.encode(type, UTF_8.name())
    val encodeQuery = URLEncoder.encode(query, UTF_8.name())
    this.navigate("$base/$encodeType/$encodeQuery") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.classicalLiteraturePeopleShowScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(peopleShowQueryArg) { NavType.StringType }
        )
    ) {
        PeopleShowRoute(
            onBackClick = onBackClick
        )
    }
}