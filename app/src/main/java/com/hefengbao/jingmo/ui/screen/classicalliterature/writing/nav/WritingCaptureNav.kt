/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.writing.nav

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.classicalliterature.writing.WritingCaptureRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

@VisibleForTesting
internal const val writingCaptureIdArg = "writingId"
private const val base = "classical_literature_writing_capture"
private const val ROUTE = "$base/{$writingCaptureIdArg}"

internal class WritingCaptureArgs(val poemId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[writingCaptureIdArg]),
                    UTF_8.name()
                )
            )
}

fun NavController.navigateToClassicalLiteratureWritingCaptureScreen(id: String) {
    val encodedId = URLEncoder.encode(id, UTF_8.name())
    this.navigate("$base/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.classicalLiteratureWritingCaptureScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(writingCaptureIdArg) { type = NavType.StringType }
        )
    ) {
        WritingCaptureRoute(
            onBackClick = onBackClick
        )
    }
}