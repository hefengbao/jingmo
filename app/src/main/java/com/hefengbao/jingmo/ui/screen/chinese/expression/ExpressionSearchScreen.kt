/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.expression

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.hefengbao.jingmo.data.database.entity.chinese.ExpressionEntity
import com.hefengbao.jingmo.ui.component.SimpleSearchScaffold

@Composable
fun ChineseExpressionSearchRoute(
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    viewModel: ExpressionSearchViewModel = hiltViewModel(),
) {
    val expressions = viewModel.expressions.collectAsLazyPagingItems()

    ChineseExpressionSearchScreen(
        onBackClick = onBackClick,
        onSearch = { viewModel.search(it) },
        onItemClick = onItemClick,
        expressions = expressions
    )
}

@Composable
private fun ChineseExpressionSearchScreen(
    onBackClick: () -> Unit,
    onSearch: (String) -> Unit,
    onItemClick: (Int) -> Unit,
    expressions: LazyPagingItems<ExpressionEntity>
) {
    var query by rememberSaveable {
        mutableStateOf("")
    }
    SimpleSearchScaffold(
        onBackClick = onBackClick,
        query = query,
        onQueryChange = { query = it },
        onSearch = onSearch
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(
                count = expressions.itemCount
            ) {
                expressions[it]?.let { entity ->
                    Text(
                        text = entity.word,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onItemClick(entity.id)
                            }
                            .padding(16.dp, 8.dp)
                    )
                }
            }
        }
    }
}