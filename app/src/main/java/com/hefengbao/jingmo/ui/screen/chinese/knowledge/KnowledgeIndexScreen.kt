/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.knowledge

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import kotlin.math.abs

@Composable
fun ChineseKnowledgeIndexRoute(
    viewModel: KnowledgeIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
) {
    val chineseKnowledge by viewModel.chineseKnowledge.collectAsState(initial = null)
    val prevId by viewModel.prevId.collectAsState(initial = null)
    val nextId by viewModel.nextId.collectAsState(initial = null)

    ChineseKnowledgeIndexScreen(
        onBackClick = onBackClick,
        onSearchClick = onSearchClick,
        chineseKnowledge = chineseKnowledge,
        prevId = prevId,
        nextId = nextId,
        setCurrentId = { viewModel.setCurrentId(it) },
        setLastReadId = {
            viewModel.setLastReadId(it)
        }
    )
}

@Composable
private fun ChineseKnowledgeIndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    chineseKnowledge: KnowledgeEntity?,
    prevId: Int?,
    nextId: Int?,
    setCurrentId: (Int) -> Unit,
    setLastReadId: (Int) -> Unit
) {
    val uriHandler = LocalUriHandler.current

    chineseKnowledge?.let { entity ->
        LaunchedEffect(entity) {
            setLastReadId(entity.id)
        }
        SimpleScaffold(
            onBackClick = onBackClick,
            title = "知识卡片",
            actions = {
                IconButton(onClick = onSearchClick) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "导航到搜索")
                }
            }
        ) {
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
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                        ) {
                            SelectionContainer {
                                Text(
                                    text = entity.content,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                            Row(
                                modifier = modifier
                                    .padding(vertical = 8.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = if (entity.url != null) Arrangement.SpaceBetween else Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "#${entity.label}",
                                    color = MaterialTheme.colorScheme.primary,
                                )
                                entity.url?.let { url ->
                                    IconButton(onClick = {
                                        uriHandler.openUri(url)
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Link,
                                            contentDescription = null
                                        )
                                    }
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
                        onClick = { setCurrentId(prevId!!) },
                        enabled = prevId != null
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                    IconButton(
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
    }
}