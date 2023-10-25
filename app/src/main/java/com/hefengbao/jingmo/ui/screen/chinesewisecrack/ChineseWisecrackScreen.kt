package com.hefengbao.jingmo.ui.screen.chinesewisecrack

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity

@Composable
fun ChineseWisecrackRoute(
    onBackClick: () -> Unit,
    onCaptureClick: (Long) -> Unit,
    viewModel: ChineseWisecrackViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.getChineseWisecrack(viewModel.id)
        viewModel.getPrevId(viewModel.id)
        viewModel.getNextId(viewModel.id)
    }

    val prevId by viewModel.prevId.collectAsState(initial = null)

    val nextId by viewModel.nextId.collectAsState(initial = null)

    val chineseWisecrack by viewModel.chineseCrack.collectAsState(initial = null)

    val searchWisecrackList by viewModel.searchWisecrackList.collectAsState(initial = emptyList())

    ChineseWisecrackScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        chineseCrack = chineseWisecrack,
        prevId = prevId,
        nextId = nextId,
        onPrevClick = {
            viewModel.getChineseWisecrack(prevId!!)
            viewModel.getPrevId(prevId!!)
            viewModel.getNextId(prevId!!)
        },
        onNextClick = {
            viewModel.getChineseWisecrack(nextId!!)
            viewModel.getPrevId(nextId!!)
            viewModel.getNextId(nextId!!)
        },
        setLastReadId = {
            viewModel.setLastReadId(it)
        },
        onSearch = { viewModel.search(it) },
        searchWisecrackList = searchWisecrackList,
        onItemClick = {
            viewModel.getChineseWisecrack(it)
            viewModel.getPrevId(it)
            viewModel.getNextId(it)
            viewModel.setLastReadId(it)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChineseWisecrackScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCaptureClick: (Long) -> Unit,
    chineseCrack: ChineseWisecrackEntity?,
    prevId: Long?,
    nextId: Long?,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    setLastReadId: (Long) -> Unit,
    onSearch: (String) -> Unit,
    searchWisecrackList: List<ChineseWisecrackEntity>,
    onItemClick: (Long) -> Unit
) {
    var showSearchBar by rememberSaveable { mutableStateOf(false) }

    BackHandler(showSearchBar) {
        showSearchBar = false
    }

    chineseCrack?.let { entity ->
        LaunchedEffect(entity) {
            setLastReadId(entity.id)
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "歇后语")
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                        }
                    },
                    actions = {
                        IconButton(onClick = { onCaptureClick(entity.id) }) {
                            Icon(imageVector = Icons.Default.Photo, contentDescription = null)
                        }
                        IconButton(onClick = { showSearchBar = true }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = null)
                        }
                    }
                )
            },
        ) { paddingValues ->
            Box(
                modifier = modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(bottom = 56.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Card(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    ) {
                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            SelectionContainer {
                                Text(
                                    text = chineseCrack.riddle,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                            SelectionContainer {
                                Text(
                                    text = "—— ${chineseCrack.answer}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(48.dp)
                        .align(
                            Alignment.BottomCenter
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = onPrevClick,
                        enabled = prevId != 0L
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }

                    IconButton(
                        onClick = onNextClick,
                        enabled = nextId != 0L
                    ) {
                        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
                    }
                }
            }
        }
    }

    if (showSearchBar) {
        SearchBar(
            showSearchBarStatusChange = { showSearchBar = it },
            onSearch = onSearch,
            searchWisecrackList = searchWisecrackList,
            onItemClick = {
                onItemClick(it)
                showSearchBar = false
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar(
    modifier: Modifier = Modifier,
    showSearchBarStatusChange: (Boolean) -> Unit,
    onSearch: (String) -> Unit,
    searchWisecrackList: List<ChineseWisecrackEntity>,
    onItemClick: (Long) -> Unit
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
            if (searchWisecrackList.isNotEmpty()) {
                val state = rememberLazyListState()

                LazyColumn(
                    modifier = modifier.fillMaxWidth(),
                    state = state,
                    content = {
                        itemsIndexed(
                            items = searchWisecrackList,
                        ) { _, item ->
                            Text(
                                modifier = modifier
                                    .clickable {
                                        onItemClick(item.id)
                                    }
                                    .padding(horizontal = 16.dp, vertical = 16.dp)
                                    .fillMaxWidth(),
                                text = item.riddle,
                            )
                            Divider(thickness = 0.5.dp)
                        }
                    }
                )
            }
        }
    }
}