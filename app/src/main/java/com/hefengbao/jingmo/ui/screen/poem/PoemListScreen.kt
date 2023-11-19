package com.hefengbao.jingmo.ui.screen.poem

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.model.PoemSimpleInfo

@Composable
fun PoemListRoute(
    modifier: Modifier = Modifier,
    viewModel: PoemListViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (Long) -> Unit
) {
    val poems by viewModel.poems.collectAsState()
    val searchPoems by viewModel.searchResult.collectAsState(emptyList())

    PoemListScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onItemClick = onItemClick,
        poems = poems,
        currentId = viewModel.id,
        onSearch = { viewModel.search(it) },
        searchPoems = searchPoems
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PoemListScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onItemClick: (Long) -> Unit,
    currentId: Long,
    poems: List<PoemSimpleInfo>,
    onSearch: (String) -> Unit,
    searchPoems: List<PoemSimpleInfo>
) {
    var showSearchBar by rememberSaveable { mutableStateOf(false) }
    var firstLoading by rememberSaveable { mutableStateOf(true) }

    BackHandler(showSearchBar) {
        showSearchBar = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "古诗词文")
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = { showSearchBar = true }) {
                        Icon(imageVector = Default.Search, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        if (poems.isNotEmpty()) {
            val state = rememberLazyListState()

            if (firstLoading) {
                val index = poems.indexOfFirst { it.id == currentId }
                LaunchedEffect(Unit) {
                    state.animateScrollToItem(index)
                }
            }

            LazyColumn(
                modifier = modifier.padding(paddingValues),
                state = state,
                content = {
                    itemsIndexed(
                        items = poems,
                    ) { _, item ->
                        Column(
                            modifier = modifier
                                .clickable {
                                    onItemClick(item.id)
                                    firstLoading = false
                                }
                                .padding(horizontal = 16.dp, vertical = 16.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(text = item.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            Text(
                                text = "${item.dynasty}·${item.writerName}",
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                        Divider(thickness = 0.5.dp)
                    }
                }
            )
        } else {
            LinearProgressIndicator(
                modifier = modifier
                    .padding(paddingValues)
                    .fillMaxWidth()
            )
        }
    }

    if (showSearchBar) {
        SearchBar(
            showSearchBarStatusChange = { showSearchBar = it },
            onSearch = onSearch,
            searchPoems = searchPoems,
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
    searchPoems: List<PoemSimpleInfo>,
    onItemClick: (Long) -> Unit
) {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(true) }

    Box(Modifier.fillMaxSize()) {
        SearchBar(
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
                    Icon(Default.ArrowBack, contentDescription = null)
                }
            },
            trailingIcon = {
                IconButton(onClick = { text = "" }) {
                    Icon(Icons.Default.Clear, contentDescription = null)
                }
            },
        ) {
            if (searchPoems.isNotEmpty()) {
                val state = rememberLazyListState()

                LazyColumn(
                    modifier = modifier.fillMaxWidth(),
                    state = state,
                    content = {
                        itemsIndexed(
                            items = searchPoems,
                        ) { _, item ->
                            Column(
                                modifier = modifier
                                    .clickable {
                                        onItemClick(item.id)
                                    }
                                    .padding(horizontal = 16.dp, vertical = 16.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = item.title,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = "${item.dynasty}·${item.writerName}",
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                            Divider(thickness = 0.5.dp)
                        }
                    }
                )
            }
        }
    }
}