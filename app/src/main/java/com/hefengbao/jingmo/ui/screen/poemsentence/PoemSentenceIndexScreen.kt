package com.hefengbao.jingmo.ui.screen.poemsentence

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.model.PoemSentenceWithBookmark
import com.hefengbao.jingmo.ui.component.BottomActionBar
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun PoemSentenceIndexRoute(
    viewModel: PoemSentenceIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    onSearchItemClick: () -> Unit,
    onBookmarksClick: () -> Unit,
) {

    val prevId by viewModel.prevId.collectAsState(initial = null)
    val nextId by viewModel.nextId.collectAsState(initial = null)
    val sentence by viewModel.sentence.collectAsState(initial = null)

    PoemSentenceIndexScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        onSearchClick = onSearchItemClick,
        sentence = sentence,
        prevId = prevId,
        nextId = nextId,
        setCurrentId = { viewModel.setCurrentId(it) },
        setUncollect = { viewModel.setUncollect(it) },
        setCollect = { viewModel.setCollect(it) },
        setLastReadId = { viewModel.setLastReadId(it) },
        onBookmarksClick = onBookmarksClick
    )
}

@Composable
private fun PoemSentenceIndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    onSearchClick: () -> Unit,
    sentence: PoemSentenceWithBookmark?,
    prevId: Int?,
    nextId: Int?,
    setCurrentId: (Int) -> Unit,
    setUncollect: (Int) -> Unit,
    setCollect: (Int) -> Unit,
    setLastReadId: (Int) -> Unit,
    onBookmarksClick: () -> Unit,
) {

    sentence?.let {
        setLastReadId(sentence.id)
        SimpleScaffold(
            onBackClick = onBackClick,
            title = "诗文名句",
            actions = {
                IconButton(onClick = onBookmarksClick) {
                    Icon(imageVector = Icons.Outlined.Bookmarks, contentDescription = "收藏")
                }
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
                        .padding(bottom = 80.dp)
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
                BottomActionBar(
                    modifier = modifier.align(Alignment.BottomCenter),
                    id = sentence.id,
                    prevId = prevId,
                    nextId = nextId,
                    setCurrentId = setCurrentId,
                    setUncollect = setUncollect,
                    setCollect = setCollect,
                    collectedAt = sentence.collectedAt
                )
            }
        }
    }
}