package com.hefengbao.jingmo.ui.screen.poem.components

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.hefengbao.jingmo.data.database.model.WritingWithBookmark
import kotlin.math.abs

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PoemShowPanel(
    modifier: Modifier = Modifier,
    writing: WritingWithBookmark,
    prevId: Int?,
    nextId: Int?,
    setCurrentId: (Int) -> Unit,
    setUncollect: (Int) -> Unit,
    setCollect: (Int) -> Unit
) {
    var isCollect = writing.collectedAt != null
    val content = buildString {
        writing.clauses.mapIndexed { index, clause ->
            /*if (clause.comments != null){
                clause.comments.map {comment ->
                    if (comment.type == CommentType.Text.name){
                        val length = clause.content.length

                    }
                }
            }else{
                append(clause.content)
            }*/
            append(clause.content)

            if (clause.breakAfter != null) {
                append("\n\n")
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .draggable(
                state = rememberDraggableState {},
                orientation = Orientation.Horizontal,
                onDragStarted = {},
                onDragStopped = {
                    if (it < 0 && abs(it) > 500f) {
                        nextId?.let {
                            setCurrentId(nextId)
                        }
                    } else if (it > 0 && abs(it) > 500f) {
                        prevId?.let {
                            setCurrentId(prevId)
                        }
                    }
                }
            )
    ) {
        SelectionContainer {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, top = 16.dp, bottom = 80.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                item {
                    Column(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        Text(
                            text = writing.title.content,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "${writing.dynasty}·${writing.author}",
                            style = MaterialTheme.typography.labelLarge
                        )
                        if (writing.preface != null) {
                            Text(
                                text = writing.preface.replace("<br />", "\n"),
                                style = MaterialTheme.typography.labelLarge,
                                fontStyle = FontStyle.Italic
                            )
                        }
                        Text(text = content, style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
            /*Column(
                modifier = modifier
                    .fillMaxWidth()
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .padding(start = 32.dp, end = 32.dp, top = 16.dp, bottom = 80.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        text = writing.title.content,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "${writing.dynasty}.${writing.author}",
                        style = MaterialTheme.typography.titleSmall
                    )
                    if (writing.preface != null) {
                        Text(
                            text = writing.preface.replace("<br />", "\n"),
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic
                        )
                    }
                    Text(text = content, style = MaterialTheme.typography.bodyLarge)
                }*/
            /*Column(
                modifier = modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = writing.type)
                if (writing.note != null) {
                    Text(text = writing.note)
                }
            }
            if (writing.comments != null) {
                SelectionContainer {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        writing.comments.map {
                            it.content?.let { content ->
                                Text(text = content)
                            }
                        }
                    }
                }
            }
        }*/
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp)
                .height(64.dp)
                .align(
                    Alignment.BottomCenter
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { setCurrentId(prevId!!) },
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
                    if (isCollect) {
                        setUncollect(writing.id)
                    } else {
                        setCollect(writing.id)
                    }
                    isCollect = !isCollect
                }
            ) {
                if (isCollect) {
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
                onClick = { setCurrentId(nextId!!) },
                enabled = nextId != null
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null
                )
            }
        }
    }
}