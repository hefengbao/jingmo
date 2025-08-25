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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.data.database.entity.chinese.ExpressionEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun ExpressionBookmarksRoute(
    viewModel: ExpressionBookmarksViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
) {
    val expressionEntities = viewModel.expressionEntities.collectAsLazyPagingItems()

    ExpressionBookmarksScreen(
        onBackClick = onBackClick,
        onItemClick = onItemClick,
        expressionEntities = expressionEntities
    )
}

@Composable
private fun ExpressionBookmarksScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    expressionEntities: LazyPagingItems<ExpressionEntity>
) {
    SimpleScaffold(onBackClick = onBackClick, title = stringResource(R.string.bookmarks)) {
        LazyColumn(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                count = expressionEntities.itemCount,
            ) {
                expressionEntities[it]?.let { entity ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onItemClick(entity.id) }
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(text = entity.pinyin, style = MaterialTheme.typography.bodyLarge)
                            Text(text = entity.word, style = MaterialTheme.typography.bodyLarge)
                            entity.explanation?.let {
                                Text(text = entity.explanation)
                            }
                        }
                    }
                }
            }
        }
    }
}