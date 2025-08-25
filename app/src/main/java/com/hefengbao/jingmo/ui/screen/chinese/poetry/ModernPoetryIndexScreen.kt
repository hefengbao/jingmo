/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.poetry

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.data.database.entity.BookmarkEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ModernPoetryEntity
import com.hefengbao.jingmo.data.enums.Category
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinese.poetry.components.ModernPoetryPanel
import kotlin.math.abs

@Composable
fun ModernPoetryIndexRoute(
    viewModel: ModernPoetryIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    onReadMoreClick: () -> Unit,
    onSearchClick: () -> Unit,
) {
    val poetryEntity by viewModel.poetryEntity.collectAsState(initial = null)
    val bookmarkEntity by viewModel.bookmarkEntity.collectAsState(initial = null)

    ModernPoetryIndexScreen(
        onBackClick = onBackClick,
        onBookmarksClick = onBookmarksClick,
        onCaptureClick = onCaptureClick,
        onReadMoreClick = onReadMoreClick,
        onSearchClick = onSearchClick,
        poetryEntity = poetryEntity,
        bookmarkEntity = bookmarkEntity,
        isBookmarked = { viewModel.isBookmarked(it, Category.ChineseModernPoetry.model) },
        addBookmark = { viewModel.addBookmark(it, Category.ChineseModernPoetry.model) },
        cancelBookmark = { viewModel.cancelBookmark(it, Category.ChineseModernPoetry.model) },
        onRefresh = { viewModel.random() },
    )
}

@Composable
private fun ModernPoetryIndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    onReadMoreClick: () -> Unit,
    onSearchClick: () -> Unit,
    poetryEntity: ModernPoetryEntity?,
    bookmarkEntity: BookmarkEntity?,
    isBookmarked: (Int) -> Unit,
    addBookmark: (Int) -> Unit,
    cancelBookmark: (Int) -> Unit,
    onRefresh: () -> Unit,
) {
    val state = rememberScrollState()

    SimpleScaffold(
        onBackClick = onBackClick,
        title = stringResource(R.string.chinese_modernpoetry),
        actions = {
            IconButton(onClick = onBookmarksClick) {
                Icon(
                    imageVector = Icons.Outlined.Bookmarks,
                    contentDescription = stringResource(R.string.bookmarks)
                )
            }
            IconButton(onClick = onReadMoreClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ReadMore,
                    contentDescription = stringResource(R.string.read_more)
                )
            }
            poetryEntity?.let {
                IconButton(onClick = { onCaptureClick(poetryEntity.id) }) {
                    Icon(
                        imageVector = Icons.Default.Photo,
                        contentDescription = stringResource(R.string.capture)
                    )
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
            poetryEntity?.let { entity ->

                LaunchedEffect(entity) { isBookmarked(entity.id) }

                BottomAppBar(
                    actions = {
                        Row(
                            modifier = modifier.padding(horizontal = 16.dp)
                        ) {
                            if (bookmarkEntity != null) {
                                IconButton(onClick = { cancelBookmark(poetryEntity.id) }) {
                                    Icon(
                                        imageVector = Icons.Default.Bookmark,
                                        contentDescription = stringResource(R.string.cancel_bookmark),
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            } else {
                                IconButton(onClick = { addBookmark(poetryEntity.id) }) {
                                    Icon(
                                        imageVector = Icons.Default.BookmarkBorder,
                                        contentDescription = stringResource(R.string.add_bookmark)
                                    )
                                }
                            }
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = onRefresh) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = stringResource(R.string.refresh)
                            )
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
                    onDragStopped = { velocity ->
                        if (velocity < 0 && abs(velocity) > 500f) {
                            onRefresh()
                        } else if (velocity > 0 && abs(velocity) > 500f) {
                            onRefresh()
                        }
                    }
                )
        ) {
            poetryEntity?.let { entity -> ModernPoetryPanel(entity = entity, state = state) }
        }
    }
}