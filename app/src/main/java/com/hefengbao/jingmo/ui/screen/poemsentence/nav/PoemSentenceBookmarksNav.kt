/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.poemsentence.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.poemsentence.PoemSentenceBookmarksRoute

private const val ROUTE = "poem_sentence_bookmarks"

fun NavController.navigateToPoemSentenceBookmarksScreen() {
    this.navigate(ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.poemSentenceBookmarksScreen(
    onBackClick: () -> Unit
) {
    composable(ROUTE) {
        PoemSentenceBookmarksRoute(
            onBackClick = onBackClick
        )
    }
}