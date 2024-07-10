/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem.nav

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem.ClassicPoemShowRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

@VisibleForTesting
internal const val classicPoemShowIdArg = "poemId"
private const val BASE = "classical_literature_classic_poem_show"
private const val ROUTE = "$BASE/{$classicPoemShowIdArg}"

internal class ClassicPoemShowArgs(val poemId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[classicPoemShowIdArg]),
                    UTF_8.name()
                )
            )
}

fun NavController.navigateToClassicalLiteratureClassicPoemShowScreen(id: Int) {
    val encodedId = URLEncoder.encode(id.toString(), UTF_8.name())
    this.navigate("$BASE/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.classicalLiteratureClassicPoemShowScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(classicPoemShowIdArg) { type = NavType.StringType }
        )
    ) {
        ClassicPoemShowRoute(
            onBackClick = onBackClick
        )
    }
}