/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinesecharacter

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Gesture
import androidx.compose.material.icons.filled.Spellcheck
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun ChineseCharacterIndexRoute(
    onBackClick: () -> Unit,
    onPinyinSearchClick: () -> Unit,
    onRadicalClickSearch: () -> Unit,
    onStrokeClick: () -> Unit,
    onStrokeSearchClick: () -> Unit,
    onSearch: (String, String) -> Unit,
) {
    ChineseCharacterIndexScreen(
        onBackClick = onBackClick,
        onPinyinSearchClick = onPinyinSearchClick,
        onRadicalClickSearch = onRadicalClickSearch,
        onStrokeClick = onStrokeClick,
        onStrokeSearchClick = onStrokeSearchClick,
        onSearch = onSearch,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
private fun ChineseCharacterIndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onPinyinSearchClick: () -> Unit,
    onRadicalClickSearch: () -> Unit,
    onStrokeClick: () -> Unit,
    onStrokeSearchClick: () -> Unit,
    onSearch: (String, String) -> Unit,
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = "汉字"
    ) {
        var query by remember {
            mutableStateOf("")
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item(
                span = { GridItemSpan(3) }
            ) {
                SearchBar(
                    query = query,
                    onQueryChange = {
                        if (it.length <= 1) {
                            query = it
                        }
                    },
                    onSearch = {
                        if (query.isNotEmpty()) {
                            onSearch(query, "char")
                        }
                    },
                    active = false,
                    onActiveChange = {},
                    trailingIcon = {
                        if (query.isNotEmpty()) {
                            IconButton(onClick = { query = "" }) {
                                Icon(imageVector = Icons.Default.Clear, contentDescription = "清除")
                            }
                        }
                    },
                    placeholder = {
                        Text(text = "例如：墨")
                    }
                ) {}
            }
            item(
                span = { GridItemSpan(3) }
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .width(5.dp)
                                .height(16.dp)
                                .align(Alignment.CenterVertically)
                                .background(color = MaterialTheme.colorScheme.onBackground)
                        )
                        Text(text = "字典", style = MaterialTheme.typography.titleLarge)
                    }
                    Divider(thickness = 2.dp)
                }
            }
            item {
                Column(
                    modifier = Modifier.clickable { onPinyinSearchClick() },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        imageVector = Icons.Default.Spellcheck,
                        contentDescription = "",
                        modifier = Modifier.size(48.dp)
                    )
                    Text(text = "拼音查询")
                }
            }
            item {
                Column(
                    modifier = Modifier.clickable { onRadicalClickSearch() },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        imageVector = Icons.Default.BarChart,
                        contentDescription = "",
                        modifier = Modifier.size(48.dp)
                    )
                    Text(text = "部首查询")
                }
            }
            item {
                Column(
                    modifier = Modifier.clickable { onStrokeSearchClick() },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "",
                        modifier = Modifier.size(48.dp)
                    )
                    Text(text = "笔画查询")
                }
            }
            item(
                span = { GridItemSpan(3) }
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .width(5.dp)
                                .height(16.dp)
                                .align(Alignment.CenterVertically)
                                .background(color = MaterialTheme.colorScheme.onBackground)
                        )
                        Text(text = "其他", style = MaterialTheme.typography.titleLarge)
                    }
                    Divider(thickness = 2.dp)
                }
            }
            item {
                Column(
                    modifier = Modifier.clickable { onStrokeClick() },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Gesture,
                        contentDescription = "",
                        modifier = Modifier.size(48.dp)
                    )
                    Text(text = "笔画演示")
                }
            }
        }
    }
}