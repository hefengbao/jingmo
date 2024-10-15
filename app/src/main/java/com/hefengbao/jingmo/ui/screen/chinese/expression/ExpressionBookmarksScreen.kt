/*
 *  This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 *  For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.expression

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.hefengbao.jingmo.data.database.entity.chinese.ExpressionEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun ExpressionBookmarksRoute(
    viewModel: ExpressionBookmarksViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
) {
    val items = viewModel.expressions.collectAsLazyPagingItems()

    ExpressionBookmarksScreen(onBackClick = onBackClick, onItemClick = onItemClick, items = items)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExpressionBookmarksScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    items: LazyPagingItems<ExpressionEntity>
) {
    SimpleScaffold(onBackClick = onBackClick, title = "收藏") {
        LazyColumn(
            modifier = modifier.padding(16.dp)
        ) {
            items(
                items = items,
            ) { entity: ExpressionEntity? ->
                entity?.let { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onItemClick(item.id) }
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(text = item.pinyin, style = MaterialTheme.typography.bodyLarge)
                            Text(text = item.word, style = MaterialTheme.typography.bodyLarge)
                            item.explanation?.let {
                                Text(text = item.explanation)
                            }
                        }
                    }
                }
            }
        }
    }
}