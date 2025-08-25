/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.writing

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingEntity
import com.hefengbao.jingmo.ui.component.SimpleSearchScaffold

@Composable
fun WritingSearchRoute(
    viewModel: WritingSearchViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (id: Int) -> Unit,
) {
    val recommendList by viewModel.recommendList.collectAsState(initial = emptyList())

    val writingEntities = viewModel.writingEntities.collectAsLazyPagingItems()

    var query: String by rememberSaveable { mutableStateOf("") }
    var type: String by rememberSaveable { mutableStateOf("keyword") }

    BackHandler(query.isNotEmpty()) {
        query = ""
    }

    WritingSearchScreen(
        onBackClick = onBackClick,
        recommendList = recommendList,
        writingEntities = writingEntities,
        onItemClick = onItemClick,
        onTypeChange = { type = it },
        query = query,
        onQueryChange = {
            query = it
            viewModel.search(it, type)
        }
    )
}

@Composable
private fun WritingSearchScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    recommendList: List<String>,
    writingEntities: LazyPagingItems<WritingEntity>,
    onItemClick: (id: Int) -> Unit,
    onTypeChange: (String) -> Unit,
    query: String,
    onQueryChange: (String) -> Unit,
) {
    SimpleSearchScaffold(
        onBackClick = onBackClick,
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        actions = {},
        floatingActionButton = {},
        placeholder = "请输入作者或关键词",
    ) {
        if (query.isEmpty()) {
            LazyVerticalGrid(
                modifier = modifier.padding(16.dp),
                columns = GridCells.Fixed(3),
            ) {
                item(
                    span = {
                        GridItemSpan(3)
                    }
                ) {
                    Text(
                        text = "作者推荐",
                        modifier = modifier.padding(8.dp),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
                items(
                    items = recommendList
                ) {
                    Text(
                        text = it, modifier = modifier
                            .clickable {
                                onTypeChange("author")
                                onQueryChange(it)
                            }
                            .padding(8.dp))
                }
            }
        } else {
            if (writingEntities.itemCount == 0) {
                Text(text = "没有查找到数据 /(ㄒoㄒ)/~~", modifier = Modifier.padding(16.dp))
            }
            List(writings = writingEntities, onItemClick = onItemClick)
        }
    }
}

@Composable
private fun List(
    modifier: Modifier = Modifier,
    writings: LazyPagingItems<WritingEntity>,
    onItemClick: (id: Int) -> Unit,
) {
    LazyColumn {
        items(
            count = writings.itemCount
        ) {
            writings[it]?.let { entity ->
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable {
                            onItemClick(entity.id)
                        }
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = entity.title.content,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "${entity.type} ${entity.dynasty}·${entity.author}",
                        style = MaterialTheme.typography.labelMedium.copy(
                            brush = null,
                            alpha = .5f
                        )
                    )
                }
            }
        }
    }
}