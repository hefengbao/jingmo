/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.wisecrack

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ReadMore
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
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
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinese.wisecrack.components.ChineseWisecrackPanel
import kotlin.math.abs

@Composable
fun ChineseWisecrackIndexRoute(
    viewModel: WisecrackIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    onReadMoreClick: () -> Unit,
    onSearchClick: () -> Unit,
    onBookmarksClick: () -> Unit,
) {
    val chineseWisecrack by viewModel.wiseCrack.collectAsState(initial = null)
    val chineseWisecrackCollection by viewModel.wiseCrackCollection.collectAsState(initial = null)

    ChineseWisecrackIndexScreen(
        onBackClick = onBackClick,
        onBookmarksClick = onBookmarksClick,
        onCaptureClick = onCaptureClick,
        onReadMoreClick = onReadMoreClick,
        onSearchClick = onSearchClick,
        chineseCrack = chineseWisecrack,
        wisecrackCollectionEntity = chineseWisecrackCollection,
        setCollect = { viewModel.setCollect(it) },
        setUncollect = { viewModel.setUncollect(it) },
        isCollect = { viewModel.isCollect(it) },
        onRefresh = viewModel::getRandom
    )
}

@Composable
private fun ChineseWisecrackIndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    onReadMoreClick: () -> Unit,
    onSearchClick: () -> Unit,
    chineseCrack: WisecrackEntity?,
    wisecrackCollectionEntity: WisecrackCollectionEntity?,
    setCollect: (Int) -> Unit,
    setUncollect: (Int) -> Unit,
    isCollect: (Int) -> Unit,
    onRefresh: () -> Unit
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = "歇后语",
        actions = {
            IconButton(onClick = onBookmarksClick) {
                Icon(imageVector = Icons.Outlined.Bookmarks, contentDescription = "收藏")
            }
            IconButton(onClick = onReadMoreClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ReadMore,
                    contentDescription = "阅读更多"
                )
            }
            IconButton(onClick = onSearchClick) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
            chineseCrack?.let { entity ->
                IconButton(onClick = { onCaptureClick(entity.id) }) {
                    Icon(imageVector = Icons.Default.Photo, contentDescription = null)
                }
            }
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    Row(
                        modifier = modifier.padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        chineseCrack?.let { entity ->
                            if (wisecrackCollectionEntity == null) {
                                IconButton(onClick = { setCollect(entity.id) }) {
                                    Icon(
                                        imageVector = Icons.Default.BookmarkBorder,
                                        contentDescription = null
                                    )
                                }
                            } else {
                                IconButton(onClick = { setUncollect(entity.id) }) {
                                    Icon(
                                        imageVector = Icons.Default.Bookmark,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = onRefresh) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "刷新")
                    }
                }
            )
        }
    ) {
        chineseCrack?.let { entity ->
            isCollect(entity.id)
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .draggable(
                        state = rememberDraggableState {},
                        orientation = Orientation.Horizontal,
                        onDragStarted = {},
                        onDragStopped = {
                            if (it < 0 && abs(it) > 500f) {
                                onRefresh()
                            } else if (it > 0 && abs(it) > 500f) {
                                onRefresh()
                            }
                        }
                    )
            ) {

                ChineseWisecrackPanel(
                    entity = entity,
                )
            }
        }
    }
}