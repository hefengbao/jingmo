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
import androidx.compose.material.icons.outlined.Photo
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.data.database.entity.BookmarkEntity
import com.hefengbao.jingmo.data.database.entity.chinese.AntitheticalCoupletEntity
import com.hefengbao.jingmo.data.enums.Category
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet.components.AntitheticalCoupletPanel
import kotlin.math.abs

@Composable
fun AntitheticalCoupletIndexRoute(
    viewModel: AntitheticalCoupletIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    onReadMoreClick: () -> Unit,
    onSearchClick: () -> Unit,
) {
    val antitheticalCoupletEntity by viewModel.antitheticalCoupletEntity.collectAsState(initial = null)
    val bookmarkEntity by viewModel.bookmarkEntity.collectAsState(initial = null)

    AntitheticalCoupletIndexScreen(
        antitheticalCoupletEntity = antitheticalCoupletEntity,
        bookmarkEntity = bookmarkEntity,
        onBackClick = onBackClick,
        onBookmarksClick = onBookmarksClick,
        onCaptureClick = onCaptureClick,
        onReadMoreClick = onReadMoreClick,
        onRefresh = { viewModel.getRandom() },
        onSearchClick = onSearchClick,
        addBookmark = { viewModel.addBookmark(it, Category.ChineseAntitheticalCouplet.model) },
        cancelBookmark = {
            viewModel.cancelBookmark(
                it,
                Category.ChineseAntitheticalCouplet.model
            )
        },
        isBookmarked = { viewModel.isBookmarked(it, Category.ChineseAntitheticalCouplet.model) }
    )
}

@Composable
private fun AntitheticalCoupletIndexScreen(
    modifier: Modifier = Modifier,
    antitheticalCoupletEntity: AntitheticalCoupletEntity?,
    bookmarkEntity: BookmarkEntity?,
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    onReadMoreClick: () -> Unit,
    onRefresh: () -> Unit,
    onSearchClick: () -> Unit,
    addBookmark: (Int) -> Unit,
    cancelBookmark: (Int) -> Unit,
    isBookmarked: (Int) -> Unit,
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = stringResource(R.string.chinese_antitheticalcouplet),
        actions = {
            IconButton(onClick = onBookmarksClick) {
                Icon(
                    imageVector = Icons.Outlined.Bookmarks,
                    contentDescription = stringResource(R.string.bookmarks)
                )
            }
            IconButton(onClick = onReadMoreClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ReadMore,
                    contentDescription = stringResource(R.string.read_more)
                )
            }
            antitheticalCoupletEntity?.let { entity ->
                IconButton(onClick = { onCaptureClick(entity.id) }) {
                    Icon(imageVector = Icons.Outlined.Photo, contentDescription = null)
                }
            }
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search)
                )
            }
        },
        bottomBar = {
            BottomAppBar(
                floatingActionButton = {
                    FloatingActionButton(onClick = onRefresh) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = stringResource(R.string.refresh)
                        )
                    }
                },
                actions = {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        IconButton(
                            onClick = {
                                antitheticalCoupletEntity?.let { entity ->
                                    if (bookmarkEntity != null) {
                                        cancelBookmark(entity.id)
                                    } else {
                                        addBookmark(entity.id)
                                    }
                                }
                            }
                        ) {
                            if (bookmarkEntity != null) {
                                Icon(
                                    imageVector = Icons.Default.Bookmark,
                                    contentDescription = stringResource(R.string.add_bookmark),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.BookmarkBorder,
                                    contentDescription = stringResource(R.string.cancel_bookmark)
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
            antitheticalCoupletEntity?.let { entity ->
                LaunchedEffect(entity) {
                    isBookmarked(entity.id)
                }
                AntitheticalCoupletPanel(antitheticalCouplet = entity)
            }
        }
    }
}