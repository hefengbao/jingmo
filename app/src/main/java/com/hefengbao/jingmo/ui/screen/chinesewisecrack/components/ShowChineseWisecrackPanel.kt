package com.hefengbao.jingmo.ui.screen.chinesewisecrack.components

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hefengbao.jingmo.data.database.model.ChineseWisecrackWithBookmark
import com.hefengbao.jingmo.ui.component.BottomActionBar
import kotlin.math.abs

@Composable
fun ShowChineseWisecrackPanel(
    modifier: Modifier = Modifier,
    entity: ChineseWisecrackWithBookmark,
    prevId: Int?,
    nextId: Int?,
    setCurrentId: (Int) -> Unit,
    setUncollect: (Int) -> Unit,
    setCollect: (Int) -> Unit
) {
    //var isCollect = entity.collectedAt != null
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
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp, 16.dp, 16.dp, 96.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            SelectionContainer {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = entity.riddle,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "—— ${entity.answer}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
        BottomActionBar(
            modifier = modifier.align(Alignment.BottomCenter),
            id = entity.id,
            prevId = prevId,
            nextId = nextId,
            setCurrentId = { setCurrentId(it) },
            setUncollect = { setUncollect(it) },
            setCollect = { setCollect(it) },
            collectedAt = entity.collectedAt
        )
        /*Row(
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
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
            IconButton(
                onClick = {
                    if (isCollect) {
                        setUncollect(entity.id)
                    } else {
                        setCollect(entity.id)
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
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
            }
        }*/
    }
}