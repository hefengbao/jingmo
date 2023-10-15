package com.hefengbao.jingmo.ui.screen.poemsentence

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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import com.hefengbao.jingmo.data.database.entity.SentenceWithPoem
import kotlinx.coroutines.launch

@Composable
fun PoemSentenceRoute(
    onBackClick: () -> Unit,
    viewModel: PoemSentenceViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.getSentence(viewModel.id)
        viewModel.getPrevId(viewModel.id)
        viewModel.getNextId(viewModel.id)
    }

    val prevId by viewModel.prevId.collectAsState(initial = null)

    val nextId by viewModel.nextId.collectAsState(initial = null)

    val sentence by viewModel.sentence.collectAsState(initial = null)

    val searchSentences by viewModel.searchSentences.collectAsState(initial = emptyList())

    PoemSentenceScreen(
        onBackClick = onBackClick,
        sentence = sentence,
        prevId = prevId,
        nextId = nextId,
        onPrevClick = {
            viewModel.getSentence(prevId!!)
            viewModel.getPrevId(prevId!!)
            viewModel.getNextId(prevId!!)
        },
        onNextClick = {
            viewModel.getSentence(nextId!!)
            viewModel.getPrevId(nextId!!)
            viewModel.getNextId(nextId!!)
        },
        setLastReadId = {
            viewModel.setLastReadId(it)
        },
        onItemClick = {
            viewModel.getSentence(it)
            viewModel.getPrevId(it)
            viewModel.getNextId(it)
            viewModel.setLastReadId(it)
        },
        onSearch = { viewModel.search(it) },
        searchSentences = searchSentences
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PoemSentenceScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    sentence: SentenceWithPoem?,
    prevId: Long?,
    nextId: Long?,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    setLastReadId: (Long) -> Unit,
    onItemClick: (Long) -> Unit,
    onSearch: (String) -> Unit,
    searchSentences: List<PoemSentenceEntity>
) {
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false
        )
    )

    var showSearchBar by rememberSaveable { mutableStateOf(false) }

    BackHandler(scaffoldState.bottomSheetState.isVisible) {
        coroutineScope.launch {
            scaffoldState.bottomSheetState.hide()
        }
    }

    BackHandler(showSearchBar) {
        showSearchBar = false
    }

    sentence?.let { entity ->
        LaunchedEffect(entity) {
            setLastReadId(entity.sentence.id)
        }
        BottomSheetScaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "古诗词文名句")
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
            sheetPeekHeight = 0.dp,
            scaffoldState = scaffoldState,
            sheetContent = {
                Column(
                    modifier = modifier
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    entity.poem?.let { poemEntity ->
                        SelectionContainer {
                            Text(
                                text = poemEntity.title,
                                modifier = modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                        SelectionContainer {
                            Text(
                                text = poemEntity.content,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
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
                                    text = sentence.sentence.content,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                            SelectionContainer {
                                Text(
                                    text = "—— ${sentence.sentence.from}",
                                    style = MaterialTheme.typography.titleMedium
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

                    OutlinedButton(
                        onClick = {
                            coroutineScope.launch {
                                scaffoldState.bottomSheetState.expand()
                            }
                        },
                        enabled = sentence.poem != null
                    ) {
                        Text(text = "原文")
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
            searchSentences = searchSentences,
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
    searchSentences: List<PoemSentenceEntity>,
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
            if (searchSentences.isNotEmpty()) {
                val state = rememberLazyListState()

                LazyColumn(
                    modifier = modifier.fillMaxWidth(),
                    state = state,
                    content = {
                        itemsIndexed(
                            items = searchSentences,
                        ) { _, item ->
                            Text(
                                modifier = modifier
                                    .clickable {
                                        onItemClick(item.id)
                                    }
                                    .padding(horizontal = 16.dp, vertical = 16.dp)
                                    .fillMaxWidth(),
                                text = item.content,
                            )
                            Divider(thickness = 0.5.dp)
                        }
                    }
                )
            }
        }
    }
}