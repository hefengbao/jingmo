/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.writing.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.classicalliterature.writing.WritingReadRoute

private const val ROUTE = "classical_literature_writing_read"

fun NavController.navigateToClassicalLiteratureWritingReadScreen() {
    this.navigate(ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.classicalLiteratureWritingReadScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
) {
    composable(ROUTE) {
        WritingReadRoute(
            onBackClick = onBackClick,
            onCaptureClick = onCaptureClick
        )
    }
}