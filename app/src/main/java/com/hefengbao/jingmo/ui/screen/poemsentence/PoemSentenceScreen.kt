package com.hefengbao.jingmo.ui.screen.poemsentence

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun PoemSentenceRoute(
    viewModel: PoemSentenceViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    onSearchItemClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getSentence(viewModel.id)
        viewModel.getPrevId(viewModel.id)
        viewModel.getNextId(viewModel.id)
    }

    val prevId by viewModel.prevId.collectAsState(initial = null)

    val nextId by viewModel.nextId.collectAsState(initial = null)

    val sentence by viewModel.sentence.collectAsState(initial = null)

    PoemSentenceScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        onSearchClick = onSearchItemClick,
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
    )
}

@Composable
private fun PoemSentenceScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    onSearchClick: () -> Unit,
    sentence: PoemSentenceEntity?,
    prevId: Int?,
    nextId: Int?,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    setLastReadId: (Int) -> Unit,
) {

    sentence?.let {
        LaunchedEffect(sentence) {
            setLastReadId(sentence.id)
        }

        SimpleScaffold(
            onBackClick = onBackClick,
            title = "诗文名句",
            actions = {
                IconButton(onClick = { onCaptureClick(sentence.id) }) {
                    Icon(imageVector = Icons.Default.Photo, contentDescription = null)
                }
                IconButton(onClick = onSearchClick) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
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
                    Row(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(top = 64.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                        ) {
                            sentence.content.split("，", "。", "？", "！").map {

                                Column {
                                    it.toCharArray().map { char ->
                                        Text(
                                            text = char.toString(),
                                            style = TextStyle.Default.copy(fontSize = 24.sp)
                                        )
                                    }
                                }
                            }
                        }
                        Column {
                            sentence.from.replace("《", "﹁")
                                .replace("》", "﹂")
                                .toCharArray()
                                .map {
                                    Text(text = it.toString())
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
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }

                    IconButton(
                        onClick = onNextClick,
                        enabled = nextId != 0
                    ) {
                        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
                    }
                }
            }
        }
    }
}