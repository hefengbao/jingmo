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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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

@Composable
fun WritingSearchRoute(
    viewModel: WritingSearchViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (id: String) -> Unit,
) {
    val recommendList by viewModel.recommendList.collectAsState(initial = emptyList())

    val writings = viewModel.writings.collectAsLazyPagingItems()

    var query: String by rememberSaveable { mutableStateOf("") }
    var type: String by rememberSaveable { mutableStateOf("keyword") }

    BackHandler(query.isNotEmpty()) {
        query = ""
    }

    WritingSearchScreen(
        onBackClick = onBackClick,
        recommendList = recommendList,
        writings = writings,
        onItemClick = onItemClick,
        onTypeChange = { type = it },
        query = query,
        onQueryChange = {
            query = it
            viewModel.search(it, type)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WritingSearchScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    recommendList: List<String>,
    writings: LazyPagingItems<WritingEntity>,
    onItemClick: (id: String) -> Unit,
    onTypeChange: (String) -> Unit,
    query: String,
    onQueryChange: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "返回"
                        )
                    }
                },
                title = {
                    SearchBar(
                        query = query,
                        onQueryChange = onQueryChange,
                        onSearch = {},
                        active = false,
                        onActiveChange = {},
                        placeholder = {
                            Text(text = "请输入作者或关键词")
                        },
                        trailingIcon = {
                            if (query.isNotEmpty()) {
                                IconButton(
                                    onClick = {
                                        onQueryChange("")
                                        onTypeChange("keyword")
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "清除"
                                    )
                                }
                            }
                        }
                    ) {

                    }
                }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = modifier.padding(paddingValues)
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
                        Text(text = it, modifier = modifier
                            .clickable {
                                onTypeChange("author")
                                onQueryChange(it)
                            }
                            .padding(8.dp))
                    }
                }
            } else {
                if (writings.itemCount == 0) {
                    Text(text = "没有查找到数据 /(ㄒoㄒ)/~~")
                }
                List(writings = writings, onItemClick = onItemClick)
            }
        }
    }
}

@Composable
private fun List(
    modifier: Modifier = Modifier,
    writings: LazyPagingItems<WritingEntity>,
    onItemClick: (id: String) -> Unit,
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
                            onItemClick(entity.id.toString())
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