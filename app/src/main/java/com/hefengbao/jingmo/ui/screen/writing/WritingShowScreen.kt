package com.hefengbao.jingmo.ui.screen.writing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.WritingCollectionEntity
import com.hefengbao.jingmo.data.database.entity.WritingEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.writing.components.WritingShowPanel
import kotlinx.serialization.json.Json

@Composable
fun WritingSearchReadRoute(
    viewModel: WritingSearchReadViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit
) {

    val writing by viewModel.writing.collectAsState()
    val writingCollectionEntity by viewModel.writingCollectionEntity.collectAsState()
    val prevId by viewModel.prevId.collectAsState()
    val nextId by viewModel.nextId.collectAsState()

    WritingSearchReadScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        writing = writing,
        writingCollectionEntity = writingCollectionEntity,
        prevId = prevId,
        nextId = nextId,
        setCurrentId = { viewModel.setCurrentId(it) },
        setCollect = {
            viewModel.setCollect(it)
        },
        setUncollect = {
            viewModel.setUncollect(it)
        },
        json = viewModel.json
    )
}

@Composable
private fun WritingSearchReadScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    writing: WritingEntity?,
    writingCollectionEntity: WritingCollectionEntity?,
    prevId: Int?,
    nextId: Int?,
    setCurrentId: (Int) -> Unit,
    setCollect: (Int) -> Unit,
    setUncollect: (Int) -> Unit,
    json: Json
) {
    writing?.let {
        SimpleScaffold(
            onBackClick = onBackClick,
            title = "诗文",
            actions = {
                IconButton(onClick = { onCaptureClick(it.id) }) {
                    Icon(imageVector = Icons.Default.Photo, contentDescription = null)
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
                                    if (writingCollectionEntity != null) {
                                        setUncollect(writing.id)
                                    } else {
                                        setCollect(writing.id)
                                    }
                                }
                            ) {
                                if (writingCollectionEntity != null) {
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
            WritingShowPanel(
                writing = it,
                prevId = prevId,
                nextId = nextId,
                setCurrentId = setCurrentId,
                json = json
            )
        }
    }
}