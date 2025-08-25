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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ReadMore
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.data.database.entity.BookmarkEntity
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeEntity
import com.hefengbao.jingmo.data.enums.Category
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinese.knowledge.components.KnowledgePanel
import kotlin.math.abs

@Composable
fun ChineseKnowledgeIndexRoute(
    viewModel: KnowledgeIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onReadMoreClick: () -> Unit,
) {
    val knowledgeEntity by viewModel.knowledgeEntity.collectAsState(initial = null)
    val bookmarkEntity by viewModel.bookmarkEntity.collectAsState(initial = null)

    ChineseKnowledgeReadScreen(
        onBackClick = onBackClick,
        onBookmarksClick = onBookmarksClick,
        onReadMoreClick = onReadMoreClick,
        onSearchClick = onSearchClick,
        knowledgeEntity = knowledgeEntity,
        bookmarkEntity = bookmarkEntity,
        addBookmark = { viewModel.addBookmark(it, Category.ChineseKnowledge.model) },
        cancelBookmark = { viewModel.cancelBookmark(it, Category.ChineseKnowledge.model) },
        isBookmarked = { viewModel.isBookmarked(it, Category.ChineseKnowledge.model) },
        refresh = { viewModel.getRandom() }
    )
}

@Composable
private fun ChineseKnowledgeReadScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    onSearchClick: () -> Unit,
    knowledgeEntity: KnowledgeEntity?,
    bookmarkEntity: BookmarkEntity?,
    addBookmark: (Int) -> Unit,
    cancelBookmark: (Int) -> Unit,
    isBookmarked: (Int) -> Unit,
    refresh: () -> Unit,
) {
    val state = rememberScrollState()

    SimpleScaffold(
        onBackClick = onBackClick,
        title = stringResource(R.string.chinese_knowledge),
        actions = {
            IconButton(onClick = onBookmarksClick) {
                Icon(
                    imageVector = Icons.Outlined.Bookmarks,
                    contentDescription = stringResource(R.string.bookmarks)
                )
            }
            IconButton(onClick = onReadMoreClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ReadMore,
                    contentDescription = stringResource(R.string.read_more)
                )
            }
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search)
                )
            }
        },
        bottomBar = {
            knowledgeEntity?.let { entity ->
                BottomAppBar(
                    actions = {
                        Row(
                            modifier = modifier.padding(horizontal = 16.dp)
                        ) {
                            if (bookmarkEntity != null) {
                                IconButton(onClick = { cancelBookmark(entity.id) }) {
                                    Icon(
                                        imageVector = Icons.Default.Bookmark,
                                        contentDescription = stringResource(R.string.cancel_bookmark),
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            } else {
                                IconButton(onClick = { addBookmark(entity.id) }) {
                                    Icon(
                                        imageVector = Icons.Default.BookmarkBorder,
                                        contentDescription = stringResource(R.string.add_bookmark)
                                    )
                                }
                            }
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = refresh) {
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
                isBookmarked(entity.id)
                KnowledgePanel(entity = entity, state = state)
            }
        }
    }
}