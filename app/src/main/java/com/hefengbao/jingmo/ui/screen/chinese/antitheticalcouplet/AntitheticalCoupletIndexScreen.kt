/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ReadMore
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.AntitheticalCoupletCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.AntitheticalCoupletEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet.components.AntitheticalCoupletPanel
import kotlin.math.abs

@Composable
fun AntitheticalCoupletIndexRoute(
    viewModel: AntitheticalCoupletIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onSearchClick: () -> Unit,
) {
    val antitheticalCouplet by viewModel.antitheticalCouplet.collectAsState(initial = null)
    val antitheticalCoupletCollection by viewModel.antitheticalCoupletCollection.collectAsState(
        initial = null
    )

    AntitheticalCoupletIndexScreen(
        antitheticalCouplet = antitheticalCouplet,
        antitheticalCoupletCollection = antitheticalCoupletCollection,
        onBackClick = onBackClick,
        onReadMoreClick = onReadMoreClick,
        onBookmarksClick = onBookmarksClick,
        onSearchClick = onSearchClick,
        onRefresh = { viewModel.getRandom() },
        setCollect = { viewModel.collect(it) },
        setUncollect = { viewModel.uncollect(it) },
        isCollect = {
            viewModel.getIdiomCollectionEntity(it)
        }
    )
}

@Composable
private fun AntitheticalCoupletIndexScreen(
    modifier: Modifier = Modifier,
    antitheticalCouplet: AntitheticalCoupletEntity?,
    antitheticalCoupletCollection: AntitheticalCoupletCollectionEntity?,
    onBackClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onSearchClick: () -> Unit,
    onRefresh: () -> Unit,
    setCollect: (Int) -> Unit,
    setUncollect: (Int) -> Unit,
    isCollect: (Int) -> Unit,
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = "对联",
        actions = {
            IconButton(onClick = onBookmarksClick) {
                Icon(imageVector = Icons.Outlined.Bookmarks, contentDescription = "收藏")
            }
            IconButton(onClick = onReadMoreClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ReadMore,
                    contentDescription = "阅读"
                )
            }
            IconButton(onClick = onSearchClick) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "查找")
            }
        },
        bottomBar = {
            BottomAppBar(
                floatingActionButton = {
                    FloatingActionButton(onClick = onRefresh) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "刷新")
                    }
                },
                actions = {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        IconButton(
                            onClick = {
                                antitheticalCouplet?.let { entity ->
                                    if (antitheticalCoupletCollection != null) {
                                        setUncollect(entity.id)
                                    } else {
                                        setCollect(entity.id)
                                    }
                                }
                            }
                        ) {
                            if (antitheticalCoupletCollection != null) {
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
                    }
                }
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
                            onRefresh()
                        } else if (velocity > 0 && abs(velocity) > 500f) {
                            onRefresh()
                        }
                    }
                )
        ) {
            antitheticalCouplet?.let { entity ->
                LaunchedEffect(entity) {
                    isCollect(entity.id)
                }
                AntitheticalCoupletPanel(antitheticalCouplet = entity)
            }
        }
    }
}