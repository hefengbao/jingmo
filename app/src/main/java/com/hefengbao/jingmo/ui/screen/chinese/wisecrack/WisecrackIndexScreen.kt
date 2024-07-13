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
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinese.wisecrack.components.ChineseWisecrackShowPanel
import kotlin.math.abs

@Composable
fun ChineseWisecrackIndexRoute(
    viewModel: WisecrackIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    onSearchClick: () -> Unit,
    onBookmarksClick: () -> Unit,
) {

    val prevId by viewModel.prevId.collectAsState()
    val nextId by viewModel.nextId.collectAsState()
    val chineseWisecrack by viewModel.chineseCrack.collectAsState()
    val chineseWisecrackCollectionEntity by viewModel.chineseWisecrackCollectionEntity.collectAsState()

    ChineseWisecrackIndexScreen(
        onBackClick = onBackClick,
        onBookmarksClick = onBookmarksClick,
        onCaptureClick = onCaptureClick,
        onSearchClick = onSearchClick,
        chineseCrack = chineseWisecrack,
        wisecrackCollectionEntity = chineseWisecrackCollectionEntity,
        prevId = prevId,
        nextId = nextId,
        setCurrentId = { viewModel.setCurrentId(it) },
        setCollect = { viewModel.setCollect(it) },
        setUncollect = { viewModel.setUncollect(it) },
        setLastReadId = {
            viewModel.setLastReadId(it)
        },
    )
}

@Composable
private fun ChineseWisecrackIndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    onSearchClick: () -> Unit,
    chineseCrack: WisecrackEntity?,
    wisecrackCollectionEntity: WisecrackCollectionEntity?,
    prevId: Int?,
    nextId: Int?,
    setCurrentId: (Int) -> Unit,
    setCollect: (Int) -> Unit,
    setUncollect: (Int) -> Unit,
    setLastReadId: (Int) -> Unit,
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = "歇后语",
        actions = {
            IconButton(onClick = onBookmarksClick) {
                Icon(imageVector = Icons.Outlined.Bookmarks, contentDescription = "收藏")
            }
            chineseCrack?.let { entity ->
                IconButton(onClick = { onCaptureClick(entity.id) }) {
                    Icon(imageVector = Icons.Default.Photo, contentDescription = null)
                }
            }
            IconButton(onClick = onSearchClick) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
        },
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    IconButton(
                        onClick = { setCurrentId(prevId!!) },
                        enabled = prevId != null
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "上一个"
                        )
                    }
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
                    IconButton(
                        onClick = { setCurrentId(nextId!!) },
                        enabled = nextId != null
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowForward,
                            contentDescription = "下一个"
                        )
                    }
                }
            }
        }
    ) {
        chineseCrack?.let { entity ->
            LaunchedEffect(entity) {
                setLastReadId(entity.id)
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

                ChineseWisecrackShowPanel(
                    entity = entity,
                )
            }
        }
    }
}