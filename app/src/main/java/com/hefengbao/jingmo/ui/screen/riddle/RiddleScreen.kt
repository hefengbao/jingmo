package com.hefengbao.jingmo.ui.screen.riddle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.RiddleEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun RiddleRoute(
    viewModel: RiddleViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onInfoClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getRiddle(viewModel.id)
        viewModel.getNextId(viewModel.id)
        viewModel.getPrevId(viewModel.id)
    }
    val riddle by viewModel.riddle.collectAsState(initial = null)
    val nextId by viewModel.nextId.collectAsState(initial = null)
    val prevId by viewModel.prevId.collectAsState(initial = null)

    RiddleScreen(
        onBackClick = onBackClick,
        onInfoClick = onInfoClick,
        onSearchClick = onSearchClick,
        onNextClick = {
            viewModel.getRiddle(nextId!!)
            viewModel.getNextId(nextId!!)
            viewModel.getPrevId(nextId!!)
        },
        onPrevClick = {
            viewModel.getRiddle(prevId!!)
            viewModel.getNextId(prevId!!)
            viewModel.getPrevId(prevId!!)
        },
        riddle = riddle,
        setLastReadId = {
            viewModel.setLastReadId(it)
        },
        prevId = prevId,
        nextId = nextId
    )
}

@Composable
private fun RiddleScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onInfoClick: () -> Unit,
    onSearchClick: () -> Unit,
    onNextClick: () -> Unit,
    onPrevClick: () -> Unit,
    riddle: RiddleEntity?,
    setLastReadId: (Int) -> Unit,
    prevId: Int?,
    nextId: Int?
) {
    var showAnswer by remember { mutableStateOf(false) }

    SimpleScaffold(
        onBackClick = onBackClick,
        title = "谜语",
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "搜素")
            }
            IconButton(onClick = onInfoClick) {
                Icon(imageVector = Icons.Default.Info, contentDescription = "点击查看谜语知识")
            }
        }
    ) {
        riddle?.let { entity ->
            LaunchedEffect(entity) {
                showAnswer = false // 默认隐藏答案
                setLastReadId(entity.id)
            }

            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 56.dp)
                ) {
                    Card(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(32.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(text = entity.puzzle)
                            if (showAnswer){
                                Text(text = entity.answer)
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
                        enabled = prevId != 0
                    ) {
                        Icon(
                            modifier = modifier.padding(8.dp),
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }

                    IconButton(onClick = { showAnswer = !showAnswer }) {
                        Icon(
                            modifier = modifier.padding(8.dp),
                            imageVector = if (showAnswer) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = null
                        )
                    }

                    IconButton(
                        onClick = onNextClick,
                        enabled = nextId != 0
                    ) {
                        Icon(
                            modifier = modifier.padding(8.dp),
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}