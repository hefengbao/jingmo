/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.writing.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.writing.WritingBookmarksRoute

private const val ROUTE = "writing_bookmarks"

fun NavController.navigateToWritingBookmarksScreen() {
    this.navigate(ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.writingBookmarksScreen(
    onBackClick: () -> Unit,
    onItemClick: (id: Int) -> Unit,
) {
    composable(ROUTE) {
        WritingBookmarksRoute(
            onBackClick = onBackClick,
            onItemClick = onItemClick
        )
    }
}