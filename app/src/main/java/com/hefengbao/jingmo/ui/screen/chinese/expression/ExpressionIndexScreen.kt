/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.expression

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.ExpressionEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinese.expression.components.ChineseExpressionShowPanel

@Composable
fun ChineseExpressionIndexRoute(
    viewModel: ExpressionIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
) {
    val expression by viewModel.expression.collectAsState(initial = null)

    ChineseExpressionIndexScreen(
        onBackClick = onBackClick,
        onSearchClick = onSearchClick,
        onRefreshClick = { viewModel.getRandom() },
        expression = expression
    )
}

@Composable
private fun ChineseExpressionIndexScreen(
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onRefreshClick: () -> Unit,
    expression: ExpressionEntity?
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = "词语",
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "查询按钮")
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onRefreshClick) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "刷新按钮")
            }
        }
    ) {
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