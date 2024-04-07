package com.hefengbao.jingmo.ui.screen.writing

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.model.WritingWithBookmark
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.writing.components.WritingShowPanel
import kotlinx.serialization.json.Json


@Composable
fun WritingBookmarksReadRoute(
    viewModel: WritingBookmarksReadViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
) {
    val writing by viewModel.writing.collectAsState(initial = null)
    val prevId by viewModel.prevId.collectAsState(initial = null)
    val nextId by viewModel.nextId.collectAsState(initial = null)
    val json = viewModel.json

    WritingBookmarksReadScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        writing = writing,
        getPrevId = { viewModel.getPrevId(it) },
        prevId = prevId,
        getNextId = { viewModel.getNextId(it) },
        nextId = nextId,
        setCurrentId = { viewModel.setCurrentId(it) },
        setUncollect = { viewModel.setUncollect(it) },
        setCollect = { viewModel.setCollect(it) },
        json = json,
    )
}

@Composable
private fun WritingBookmarksReadScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    writing: WritingWithBookmark?,
    getPrevId: (Long) -> Unit,
    prevId: Int?,
    getNextId: (Long) -> Unit,
    nextId: Int?,
    setCurrentId: (Int) -> Unit,
    setUncollect: (Int) -> Unit,
    setCollect: (Int) -> Unit,
    json: Json
) {
    writing?.let {
        LaunchedEffect(writing) {
            it.collectedAt?.let { collectedAt ->
                getNextId(collectedAt)
                getPrevId(collectedAt)
            }
        }
        SimpleScaffold(
            onBackClick = onBackClick,
            title = "诗文",
            actions = {
                IconButton(onClick = { onCaptureClick(it.id) }) {
                    Icon(imageVector = Icons.Default.Photo, contentDescription = null)
                }
            }
        ) {
            WritingShowPanel(
                writing = it,
                prevId = prevId,
                nextId = nextId,
                setCurrentId = setCurrentId,
                setUncollect = setUncollect,
                setCollect = setCollect,
                json = json
            )
        }
    }
}