package com.hefengbao.jingmo.ui.screen.poemsentence

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.SentenceWithPoem
import kotlinx.coroutines.launch

@Composable
fun PoemSentenceSearchShowRoute(
    onBackClick: () -> Unit,
    onCaptureClick: (Long) -> Unit,
    viewModel: PoemSentenceSearchShowViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.getSentence(viewModel.id)
        viewModel.getPrevId(viewModel.id, viewModel.query)
        viewModel.getNextId(viewModel.id, viewModel.query)
    }

    val prevId by viewModel.prevId.collectAsState(initial = null)

    val nextId by viewModel.nextId.collectAsState(initial = null)

    val sentence by viewModel.sentence.collectAsState(initial = null)

    PoemSentenceSearchShowScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        sentence = sentence,
        prevId = prevId,
        nextId = nextId,
        onPrevClick = {
            viewModel.getSentence(prevId!!)
            viewModel.getPrevId(prevId!!, viewModel.query)
            viewModel.getNextId(prevId!!, viewModel.query)
        },
        onNextClick = {
            viewModel.getSentence(nextId!!)
            viewModel.getPrevId(nextId!!, viewModel.query)
            viewModel.getNextId(nextId!!, viewModel.query)
        },
        query = viewModel.query
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
private fun PoemSentenceSearchShowScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCaptureClick: (Long) -> Unit,
    sentence: SentenceWithPoem?,
    prevId: Long?,
    nextId: Long?,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    query: String
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
        BottomSheetScaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "搜索：$query")
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                        }
                    },
                    actions = {
                        IconButton(onClick = { onCaptureClick(entity.sentence.id) }) {
                            Icon(imageVector = Icons.Default.Photo, contentDescription = null)
                        }
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
}
