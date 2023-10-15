package com.hefengbao.jingmo.ui.screen.idiom

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.model.SimpleIdiomInfo

@Composable
fun IdiomListRoute(
    modifier: Modifier = Modifier,
    viewModel: IdiomListViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (Long) -> Unit,
) {
    val idioms by viewModel.idioms.collectAsState()
    val searchIdioms by viewModel.searchResult.collectAsState(initial = emptyList())

    IdiomListScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onItemClick = onItemClick,
        currentId = viewModel.id,
        idioms = idioms,
        onSearch = { viewModel.search(it) },
        searchIdioms = searchIdioms
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun IdiomListScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onItemClick: (Long) -> Unit,
    currentId: Long,
    idioms: List<SimpleIdiomInfo>,
    onSearch: (String) -> Unit,
    searchIdioms: List<SimpleIdiomInfo>
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
                    Text(text = "成语")
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
        },
    ) { paddingValues ->
        if (idioms.isNotEmpty()) {
            val state = rememberLazyListState()

            if (firstLoading) {
                val index = idioms.indexOfFirst { it.id == currentId }
                LaunchedEffect(Unit) {
                    state.animateScrollToItem(index)
                }
            }

            LazyColumn(
                modifier = modifier.padding(paddingValues),
                state = state,
                content = {
                    itemsIndexed(items = idioms, key = { _, item -> item.id }) { _, item ->
                        Text(
                            text = item.word,
                            modifier = modifier
                                .clickable {
                                    onItemClick(item.id)
                                    firstLoading = false
                                }
                                .padding(16.dp)
                                .fillMaxWidth()
                        )
                        Divider(modifier = modifier.fillMaxWidth(), thickness = 0.5.dp)
                    }
                }
            )
        } else {
            LinearProgressIndicator(modifier = modifier.fillMaxWidth())
        }
    }

    if (showSearchBar) {
        SearchBar(
            showSearchBarStatusChange = { showSearchBar = it },
            onSearch = onSearch,
            searchIdioms = searchIdioms,
            onItemClick = onItemClick,
            changeFistLoading = { firstLoading = false }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    showSearchBarStatusChange: (Boolean) -> Unit,
    onSearch: (String) -> Unit,
    searchIdioms: List<SimpleIdiomInfo>,
    onItemClick: (Long) -> Unit,
    changeFistLoading: () -> Unit
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
            if (searchIdioms.isNotEmpty()) {
                val state = rememberLazyListState()

                LazyColumn(
                    modifier = modifier.fillMaxWidth(),
                    state = state,
                    content = {
                        itemsIndexed(
                            items = searchIdioms,
                        ) { _, item ->
                            Text(
                                text = item.word,
                                modifier = modifier
                                    .clickable {
                                        onItemClick(item.id)
                                        changeFistLoading()
                                    }
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            )
                            Divider(thickness = 0.5.dp)
                        }
                    }
                )
            }
        }
    }
}