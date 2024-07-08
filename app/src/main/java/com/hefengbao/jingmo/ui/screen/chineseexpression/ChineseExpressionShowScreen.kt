/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chineseexpression

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.ChineseExpressionEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chineseexpression.components.ChineseExpressionShowPanel

@Composable
fun ChineseExpressionShowRoute(
    onBackClick: () -> Unit,
    viewModel: ChineseExpressionShowViewModel = hiltViewModel()
) {
    val expression by viewModel.expression.collectAsState(initial = null)

    ChineseExpressionShowScreen(onBackClick = onBackClick, expression = expression)
}

@Composable
private fun ChineseExpressionShowScreen(
    onBackClick: () -> Unit,
    expression: ChineseExpressionEntity?
) {
    SimpleScaffold(onBackClick = onBackClick, title = "词语") {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            expression?.let { entity ->
                ChineseExpressionShowPanel(entity = entity)
            }
        }
    }
}