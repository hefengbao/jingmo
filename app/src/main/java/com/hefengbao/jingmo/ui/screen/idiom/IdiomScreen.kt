package com.hefengbao.jingmo.ui.screen.idiom

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
import androidx.compose.material3.Card
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.IdiomEntity

@Composable
fun IdiomRoute(
    onBackClick: () -> Unit,
    viewModel: IdiomViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.getIdiom(viewModel.id)
        viewModel.getPrevId(viewModel.id)
        viewModel.getNextId(viewModel.id)
    }

    val prevId by viewModel.prevId.collectAsState(initial = null)

    val nextId by viewModel.nextId.collectAsState(initial = null)

    val idiom by viewModel.idiom.collectAsState(initial = null)

    IdiomScreen(
        onBackClick = onBackClick,
        idiom = idiom,
        prevId = prevId,
        nextId = nextId,
        onPrevClick = {
            viewModel.getIdiom(prevId!!)
            viewModel.getPrevId(prevId!!)
            viewModel.getNextId(prevId!!)
        },
        onNextClick = {
            viewModel.getIdiom(nextId!!)
            viewModel.getPrevId(nextId!!)
            viewModel.getNextId(nextId!!)
        },
        setLastReadId = {
            viewModel.setLastReadId(it)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun IdiomScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    idiom: IdiomEntity?,
    prevId: Long?,
    nextId: Long?,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    setLastReadId: (Long) -> Unit
) {
    idiom?.let { entity ->
        LaunchedEffect(entity) {
            setLastReadId(entity.id)
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
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                SelectionContainer {
                                    Text(
                                        text = idiom.pinyin,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                                SelectionContainer {
                                    Text(
                                        text = entity.word,
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                            }
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "释义",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                SelectionContainer {
                                    Text(
                                        text = idiom.explanation,
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                            }

                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "示例",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                SelectionContainer {
                                    Text(
                                        text = idiom.example,
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                            }

                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "出处",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                SelectionContainer {
                                    Text(
                                        text = idiom.derivation,
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
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
}