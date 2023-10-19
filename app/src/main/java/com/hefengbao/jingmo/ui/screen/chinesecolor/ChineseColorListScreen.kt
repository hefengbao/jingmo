package com.hefengbao.jingmo.ui.screen.chinesecolor

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.model.ChineseColor

@Composable
fun ChineseColorRoute(
    viewModel: ChineseColorListViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (String) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getList()
    }

    val chineseColors by viewModel.chineseColors.collectAsState(initial = emptyList())
    val searchColors by viewModel.searchChineseColors.collectAsState(initial = emptyList())

    ChineseColorScreen(
        onBackClick = onBackClick,
        onSearch = { viewModel.search(it) },
        searchColors = searchColors,
        chineseColors = chineseColors,
        onItemClick = onItemClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChineseColorScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSearch: (String) -> Unit,
    searchColors: List<ChineseColor>,
    chineseColors: List<ChineseColor>,
    onItemClick: (String) -> Unit
) {
    var showSearchBar by rememberSaveable { mutableStateOf(false) }

    BackHandler(showSearchBar) {
        showSearchBar = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "中国传统色")
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = { showSearchBar = true }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier.padding(paddingValues),
            content = {
                itemsIndexed(
                    items = chineseColors,
                    key = { _, item -> item.id },
                ) { _, item ->
                    Item(item = item, onItemClick = onItemClick)
                }
            }
        )
    }

    if (showSearchBar) {
        SearchBar(
            showSearchBarStatusChange = { showSearchBar = it },
            onSearch = onSearch,
            searchColors = searchColors,
            onItemClick = onItemClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    showSearchBarStatusChange: (Boolean) -> Unit,
    onSearch: (String) -> Unit,
    searchColors: List<ChineseColor>,
    onItemClick: (String) -> Unit
) {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(true) }

    Box(Modifier.fillMaxSize()) {
        androidx.compose.material3.SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter),
            query = text,
            onQueryChange = { text = it },
            onSearch = {
                active = true
                if (text.isNotEmpty()) {
                    onSearch(text)
                }
            },
            active = active,
            onActiveChange = {
                active = it
                showSearchBarStatusChange(it)
            },
            placeholder = { Text("请输入") },
            leadingIcon = {
                IconButton(onClick = { showSearchBarStatusChange(false) }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
            },
            trailingIcon = {
                IconButton(onClick = { text = "" }) {
                    Icon(Icons.Default.Clear, contentDescription = null)
                }
            },
        ) {
            if (searchColors.isNotEmpty()) {
                val state = rememberLazyListState()

                LazyColumn(
                    modifier = modifier.fillMaxWidth(),
                    state = state,
                    content = {
                        itemsIndexed(
                            items = searchColors,
                        ) { _, item ->
                            Item(item = item, onItemClick = onItemClick)
                        }
                    }
                )
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