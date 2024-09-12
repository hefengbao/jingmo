/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.knowledge.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.chinese.knowledge.ChineseKnowledgeReadRoute

private const val ROUTE = "chinese_knowledge_read"

fun NavController.navigateToChineseKnowledgeReadScreen() {
    this.navigate(ROUTE)
}

fun NavGraphBuilder.chineseKnowledgeReadScreen(
    onBackClick: () -> Unit,
) {
    composable(ROUTE) {
        ChineseKnowledgeReadRoute(
            onBackClick = onBackClick,
        )
    }
}