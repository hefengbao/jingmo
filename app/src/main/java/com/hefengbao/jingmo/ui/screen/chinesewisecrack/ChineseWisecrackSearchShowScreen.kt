package com.hefengbao.jingmo.ui.screen.chinesewisecrack

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
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity

@Composable
fun ChineseWisecrackSearchShowRoute(
    viewModel: ChineseWisecrackSearchShowViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCaptureClick: (Long) -> Unit,
) {

    LaunchedEffect(Unit) {
        viewModel.getChineseWisecrack(viewModel.id)
        viewModel.getPrevId(viewModel.id, viewModel.query)
        viewModel.getNextId(viewModel.id, viewModel.query)
    }

    val prevId by viewModel.prevId.collectAsState(initial = null)

    val nextId by viewModel.nextId.collectAsState(initial = null)

    val chineseWisecrack by viewModel.chineseCrack.collectAsState(initial = null)

    ChineseWisecrackSearchShowScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        chineseCrack = chineseWisecrack,
        prevId = prevId,
        nextId = nextId,
        onPrevClick = {
            viewModel.getChineseWisecrack(prevId!!)
            viewModel.getPrevId(prevId!!, viewModel.query)
            viewModel.getNextId(prevId!!, viewModel.query)
        },
        onNextClick = {
            viewModel.getChineseWisecrack(nextId!!)
            viewModel.getPrevId(nextId!!, viewModel.query)
            viewModel.getNextId(nextId!!, viewModel.query)
        },
        query = viewModel.query
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChineseWisecrackSearchShowScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCaptureClick: (Long) -> Unit,
    chineseCrack: ChineseWisecrackEntity?,
    prevId: Long?,
    nextId: Long?,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    query: String
) {

    chineseCrack?.let { entity ->
        Scaffold(
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
                        IconButton(onClick = { onCaptureClick(entity.id) }) {
                            Icon(imageVector = Icons.Default.Photo, contentDescription = null)
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
}