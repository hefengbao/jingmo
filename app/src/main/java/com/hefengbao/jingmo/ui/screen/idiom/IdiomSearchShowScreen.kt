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
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun IdiomSearchShowRoute(
    onBackClick: () -> Unit,
    onCaptureClick: (Long) -> Unit,
    viewModel: IdiomSearchShowViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.getIdiom(viewModel.id)
        viewModel.getPrevId(viewModel.id, viewModel.query)
        viewModel.getNextId(viewModel.id, viewModel.query)
    }

    val prevId by viewModel.prevId.collectAsState(initial = null)

    val nextId by viewModel.nextId.collectAsState(initial = null)

    val idiom by viewModel.idiom.collectAsState(initial = null)

    IdiomSearchShowScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        idiom = idiom,
        prevId = prevId,
        nextId = nextId,
        onPrevClick = {
            viewModel.getIdiom(prevId!!)
            viewModel.getPrevId(prevId!!, viewModel.query)
            viewModel.getNextId(prevId!!, viewModel.query)
        },
        onNextClick = {
            viewModel.getIdiom(nextId!!)
            viewModel.getPrevId(nextId!!, viewModel.query)
            viewModel.getNextId(nextId!!, viewModel.query)
        }
    )
}

@Composable
private fun IdiomSearchShowScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCaptureClick: (Long) -> Unit,
    idiom: IdiomEntity?,
    prevId: Long?,
    nextId: Long?,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    idiom?.let { entity ->
        SimpleScaffold(
            onBackClick = onBackClick,
            title = "成语",
            actions = {
                IconButton(onClick = { onCaptureClick(entity.id) }) {
                    Icon(imageVector = Icons.Default.Photo, contentDescription = null)
                }
            }
        ) {
            Box(
                modifier = modifier
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
                                    style = MaterialTheme.typography.titleLarge
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
                                    style = MaterialTheme.typography.titleLarge
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
                                    style = MaterialTheme.typography.titleLarge
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