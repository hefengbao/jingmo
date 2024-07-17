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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinese.knowledge.components.KnowledgeShowPanel
import kotlin.math.abs

@Composable
fun ChineseKnowledgeIndexRoute(
    viewModel: KnowledgeIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onBookmarksClick: () -> Unit,
) {
    val knowledgeEntity by viewModel.knowledgeEntity.collectAsState(initial = null)
    val knowledgeCollectionEntity by viewModel.knowledgeCollectionEntity.collectAsState(initial = null)

    ChineseKnowledgeIndexScreen(
        onBackClick = onBackClick,
        onSearchClick = onSearchClick,
        onBookmarksClick = onBookmarksClick,
        knowledgeEntity = knowledgeEntity,
        knowledgeCollectionEntity = knowledgeCollectionEntity,
        setCollect = { viewModel.collect(it) },
        setUncollect = { viewModel.uncollect(it) },
        isCollect = { viewModel.isCollect(it) },
        refresh = { viewModel.random() }
    )
}

@Composable
private fun ChineseKnowledgeIndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onSearchClick: () -> Unit,
    knowledgeEntity: KnowledgeEntity?,
    knowledgeCollectionEntity: KnowledgeCollectionEntity?,
    setCollect: (Int) -> Unit,
    setUncollect: (Int) -> Unit,
    isCollect: (Int) -> Unit,
    refresh: () -> Unit,
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = "知识卡片",
        actions = {
            IconButton(onClick = onBookmarksClick) {
                Icon(imageVector = Icons.Default.Bookmarks, contentDescription = "")
            }
            IconButton(onClick = onSearchClick) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "")
            }
        },
        bottomBar = {
            knowledgeEntity?.let { entity ->
                BottomAppBar(
                    actions = {
                        Row(
                            modifier = modifier.padding(horizontal = 16.dp)
                        ) {
                            if (knowledgeCollectionEntity != null) {
                                IconButton(onClick = { setUncollect(entity.id) }) {
                                    Icon(
                                        imageVector = Icons.Default.Bookmark,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            } else {
                                IconButton(onClick = { setCollect(entity.id) }) {
                                    Icon(
                                        imageVector = Icons.Default.BookmarkBorder,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = refresh) {
                            Icon(imageVector = Icons.Default.Refresh, contentDescription = "")
                        }
                    }
                )
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
                            refresh()
                        } else if (it > 0 && abs(it) > 500f) {
                            refresh()
                        }
                    }
                )
        ) {
            knowledgeEntity?.let { entity ->
                isCollect(entity.id)
                KnowledgeShowPanel(entity = entity)
            }
        }
    }
}