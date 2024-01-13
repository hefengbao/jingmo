package com.hefengbao.jingmo.ui.screen.poem

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.hefengbao.jingmo.data.database.entity.WritingEntity
import com.hefengbao.jingmo.data.database.model.PoemSimpleInfo

@Composable
fun PoemListRoute(
    viewModel: PoemListViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (id: String, type: String, query: String) -> Unit,
) {
    //val poems by viewModel.poems.collectAsState()
    //val searchPoems by viewModel.searchResult.collectAsState(emptyList())
    val writings = viewModel.writings.collectAsLazyPagingItems()

    var query: String by rememberSaveable {
        if (viewModel.type == "author") {
            mutableStateOf(viewModel.query)
        } else {
            mutableStateOf("")
        }
    }

    /*LaunchedEffect(Unit) {
        if (viewModel.type == "author") {
            viewModel.searchByAuthor(viewModel.query)
        }
    }*/

    Screen(
        onBackClick = onBackClick,
        writings = writings,
        onItemClick = onItemClick,
        type = viewModel.type,
        query = query,
        onQueryChange = {
            query = it
            viewModel.search(it)
        }
    )

    /*PoemListScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onItemClick = onItemClick,
        onSearchItemClick = onSearchItemClick,
        poems = poems,
        currentId = viewModel.id,
        onSearch = { viewModel.search(it) },
        searchPoems = searchPoems
    )*/
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Screen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    writings: LazyPagingItems<WritingEntity>,
    onItemClick: (id: String, type: String, query: String) -> Unit,
    type: String,
    query: String,
    onQueryChange: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (type == "author") {
                        Text(text = query)
                    } else {
                        BasicTextField(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(end = 16.dp),
                            value = query,
                            onValueChange = {
                                onQueryChange(it)
                            },
                            singleLine = true,
                            /*keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Search
                            ),*/
                            keyboardActions = KeyboardActions {

                            },
                            textStyle = TextStyle(
                                fontSize = 14.sp
                            ),
                            decorationBox = { innerTextField ->
                                Row(
                                    modifier = modifier
                                        .background(
                                            color = MaterialTheme.colorScheme.primaryContainer.copy(
                                                alpha = .5f
                                            ),
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                        .padding(16.dp, 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    innerTextField()
                                }
                            }
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = modifier.padding(paddingValues)
        ) {
            LazyColumn {
                items(
                    items = writings
                ) {
                    it?.let { entity ->
                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .clickable {
                                    onItemClick(entity.id.toString(), type, query)
                                }
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = entity.title.content,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "${entity.type} ${entity.dynasty}·${entity.author}",
                                style = MaterialTheme.typography.titleSmall.copy(color = Color.Gray)
                            )
                        }
                    }
                }
            }
        }
    }
    /*SimpleScaffold(
        onBackClick = onBackClick,
        title = "诗文"
    ){

    }*/
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PoemListScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onItemClick: (Long) -> Unit,
    onSearchItemClick: (Long, String) -> Unit,
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
            onSearchItemClick = onSearchItemClick
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    showSearchBarStatusChange: (Boolean) -> Unit,
    onSearch: (String) -> Unit,
    searchPoems: List<PoemSimpleInfo>,
    onSearchItemClick: (Long, String) -> Unit
) {
    var query by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(true) }
    val keyboard = LocalSoftwareKeyboardController.current

    Box(Modifier.fillMaxSize()) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter),
            query = query,
            onQueryChange = { query = it },
            onSearch = {
                active = true
                if (query.isNotEmpty()) {
                    onSearch(query)
                    keyboard?.hide()
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
                IconButton(onClick = { query = "" }) {
                    Icon(Default.Clear, contentDescription = null)
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
                                        onSearchItemClick(item.id, query)
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