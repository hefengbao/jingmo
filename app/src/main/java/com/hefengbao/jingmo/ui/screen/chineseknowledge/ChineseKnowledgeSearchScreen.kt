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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.ChineseKnowledgeEntity

@Composable
fun ChineseKnowledgeSearchRoute(
    viewModel: ChineseKnowledgeSearchViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val chineseKnowLedgeList by viewModel.chineseKnowledgeList.collectAsState(initial = emptyList())
    var query by rememberSaveable { mutableStateOf("") }

    ChineseKnowledgeSearchScreen(
        onBackClick = onBackClick,
        chineseKnowLedgeList = chineseKnowLedgeList,
        query = query,
        onQueryChange = { query = it },
        onSearch = {
            viewModel.search(query)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
private fun ChineseKnowledgeSearchScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    chineseKnowLedgeList: List<ChineseKnowledgeEntity>,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    val uriHandler = LocalUriHandler.current
    val keyboard = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    SearchBar(
                        query = query,
                        onQueryChange = onQueryChange,
                        onSearch = {
                            onSearch(query)
                            keyboard?.hide()
                        },
                        active = false,
                        onActiveChange = {},
                    ) {}
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
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