package com.hefengbao.jingmo.ui.screen.poemsentence

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.PoemSentenceCollectionEntity
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import kotlin.math.abs

@Composable
fun PoemSentenceIndexRoute(
    viewModel: PoemSentenceIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    onSearchItemClick: () -> Unit,
    onBookmarksClick: () -> Unit,
) {

    val prevId by viewModel.prevId.collectAsState()
    val nextId by viewModel.nextId.collectAsState()
    val sentence by viewModel.sentence.collectAsState()
    val poemSentenceCollectionEntity by viewModel.poemSentenceCollectionEntity.collectAsState()

    PoemSentenceIndexScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        onSearchClick = onSearchItemClick,
        sentence = sentence,
        poemSentenceCollectionEntity = poemSentenceCollectionEntity,
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
    sentence: PoemSentenceEntity?,
    poemSentenceCollectionEntity: PoemSentenceCollectionEntity?,
    prevId: Int?,
    nextId: Int?,
    setCurrentId: (Int) -> Unit,
    setUncollect: (Int) -> Unit,
    setCollect: (Int) -> Unit,
    setLastReadId: (Int) -> Unit,
    onBookmarksClick: () -> Unit,
) {

    sentence?.let {
        LaunchedEffect(it) {
            setLastReadId(sentence.id)
        }
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
            },
            bottomBar = {
                BottomAppBar(
                    actions = {
                        Row(
                            modifier = modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = {
                                    setCurrentId(prevId!!)
                                },
                                enabled = prevId != null
                            ) {
                                Icon(
                                    modifier = modifier.padding(8.dp),
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = null
                                )
                            }
                            IconButton(
                                onClick = {
                                    if (poemSentenceCollectionEntity != null) {
                                        setUncollect(it.id)
                                    } else {
                                        setCollect(it.id)
                                    }
                                }
                            ) {
                                if (poemSentenceCollectionEntity != null) {
                                    Icon(
                                        imageVector = Icons.Default.Bookmark,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.BookmarkBorder,
                                        contentDescription = null
                                    )
                                }
                            }
                            IconButton(
                                modifier = modifier.padding(8.dp),
                                onClick = {
                                    setCurrentId(nextId!!)
                                },
                                enabled = nextId != null
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = null
                                )
                            }
                        }
                    },
                )
            }
        ) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .draggable(
                        state = rememberDraggableState {},
                        orientation = Orientation.Horizontal,
                        onDragStarted = {},
                        onDragStopped = { velocity ->
                            if (velocity < 0 && abs(velocity) > 500f) {
                                nextId?.let {
                                    setCurrentId(nextId)
                                }
                            } else if (velocity > 0 && abs(velocity) > 500f) {
                                prevId?.let {
                                    setCurrentId(prevId)
                                }
                            }
                        }
                    )
            ) {
                Row(
                    modifier = modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        sentence.content.split("，", "。", "？", "！", "、").map {

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
                            .map { text ->
                                Text(text = text.toString())
                            }
                    }
                }
            }
        }
    }
}