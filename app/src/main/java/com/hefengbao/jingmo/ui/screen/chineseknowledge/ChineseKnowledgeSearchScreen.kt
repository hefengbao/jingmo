/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chineseknowledge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.ChineseKnowledgeEntity
import com.hefengbao.jingmo.ui.component.SimpleSearchScaffold

@Composable
fun ChineseKnowledgeSearchRoute(
    viewModel: ChineseKnowledgeSearchViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val chineseKnowLedgeList by viewModel.chineseKnowledgeList.collectAsState(initial = emptyList())

    ChineseKnowledgeSearchScreen(
        onBackClick = onBackClick,
        chineseKnowLedgeList = chineseKnowLedgeList,
        onSearch = {
            viewModel.search(it)
        }
    )
}

@Composable
private fun ChineseKnowledgeSearchScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    chineseKnowLedgeList: List<ChineseKnowledgeEntity>,
    onSearch: (String) -> Unit
) {
    var query by rememberSaveable { mutableStateOf("") }
    val uriHandler = LocalUriHandler.current

    SimpleSearchScaffold(
        onBackClick = onBackClick,
        query = query,
        onQueryChange = {
            query = it
            if (query.isNotEmpty()) {
                onSearch(query)
            }
        },
        onSearch = onSearch
    ) {
        LazyColumn(
            modifier = modifier
                .padding(16.dp),
            content = {
                if (chineseKnowLedgeList.isEmpty()) {
                    item(content = {
                        Text(
                            text = "没有数据",
                            modifier = modifier.fillMaxSize(),
                            textAlign = TextAlign.Center
                        )
                    })
                } else {
                    itemsIndexed(
                        items = chineseKnowLedgeList,
                        key = { _: Int, item: ChineseKnowledgeEntity -> item.id }
                    ) { _: Int, item: ChineseKnowledgeEntity ->
                        Card(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                        ) {
                            Column(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                            ) {
                                SelectionContainer {
                                    Text(
                                        text = item.content,
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                                Row(
                                    modifier = modifier
                                        .padding(vertical = 8.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = if (item.url != null) Arrangement.SpaceBetween else Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "#${item.label}",
                                        color = MaterialTheme.colorScheme.primary,
                                    )
                                    item.url?.let { url ->
                                        IconButton(onClick = {
                                            uriHandler.openUri(url)
                                        }) {
                                            Icon(
                                                imageVector = Icons.Default.Link,
                                                contentDescription = null
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}