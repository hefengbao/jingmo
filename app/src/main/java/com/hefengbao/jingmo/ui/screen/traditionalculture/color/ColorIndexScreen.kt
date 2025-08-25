/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.traditionalculture.color

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.model.traditionalculture.ChineseColor
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun ColorIndexRoute(
    viewModel: ColorIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (String) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getList()
    }

    val chineseColors by viewModel.colors.collectAsState(initial = emptyList())
    val searchColors by viewModel.searchColors.collectAsState(initial = emptyList())

    ColorIndexScreen(
        onBackClick = onBackClick,
        onSearch = { viewModel.search(it) },
        searchColors = searchColors,
        colors = chineseColors,
        onItemClick = onItemClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ColorIndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSearch: (String) -> Unit,
    searchColors: List<ChineseColor>,
    colors: List<ChineseColor>,
    onItemClick: (String) -> Unit
) {
    var showSearchBar by rememberSaveable { mutableStateOf(false) }

    BackHandler(showSearchBar) {
        showSearchBar = false
    }

    var query by rememberSaveable {
        mutableStateOf("")
    }

    val keyboard = LocalSoftwareKeyboardController.current

    SimpleScaffold(
        onBackClick = onBackClick,
        title = "传统色",
        actions = {
            if (showSearchBar) {
                IconButton(
                    onClick = {
                        showSearchBar = false
                        query = ""
                    }
                ) {
                    Icon(imageVector = Icons.Default.SearchOff, contentDescription = null)
                }
            } else {
                IconButton(
                    onClick = { showSearchBar = true }
                ) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            }
        }
    ) {
        if (showSearchBar) {
            LazyColumn(
                modifier = modifier.fillMaxWidth(),
                state = rememberLazyListState()
            ) {
                item {
                    if (showSearchBar) {
                        SearchBar(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            query = query,
                            onQueryChange = { query = it },
                            onSearch = {
                                onSearch(query)
                                keyboard?.hide()
                            },
                            active = false,
                            onActiveChange = {},
                            placeholder = {
                                Text(text = "请输入")
                            }
                        ) {}
                    }
                }
                itemsIndexed(
                    items = searchColors,
                    key = { _, item -> item.id },
                ) { _, item ->
                    Item(item = item, onItemClick = onItemClick)
                }
            }
        } else {
            LazyColumn(
                modifier = modifier.fillMaxWidth(),
                state = rememberLazyListState()
            ) {
                itemsIndexed(
                    items = colors,
                    key = { _, item -> item.id },
                ) { _, item ->
                    Item(item = item, onItemClick = onItemClick)
                }
            }
        }
    }
}

@Composable
private fun Item(
    modifier: Modifier = Modifier,
    item: ChineseColor,
    onItemClick: (String) -> Unit
) {
    Card(
        modifier = modifier
            .padding(16.dp, 8.dp)
            .clickable {
                onItemClick(item.id)
            }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                modifier = modifier.size(48.dp),
                imageVector = Icons.Default.ColorLens,
                tint = Color(item.hex.toColorInt()),
                contentDescription = null
            )
            Column {
                Text(text = item.pinyin)
                Text(text = item.name)
            }
        }
    }
}