/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.expression

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.data.database.entity.BookmarkEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ExpressionEntity
import com.hefengbao.jingmo.data.enums.Category
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinese.expression.components.ExpressionPanel
import kotlin.math.abs

@Composable
fun ChineseExpressionIndexRoute(
    viewModel: ExpressionIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onSearchClick: () -> Unit,
) {
    val expression by viewModel.expressionEntity.collectAsState(initial = null)
    val expressionCollectionEntity by viewModel.bookmarkEntity.collectAsState(initial = null)

    LaunchedEffect(expression) {
        expression?.let {
            viewModel.isBookmarked(it.id, Category.ChineseExpression.model)
        }
    }

    ChineseExpressionIndexScreen(
        onBackClick = onBackClick,
        onBookmarksClick = onBookmarksClick,
        onSearchClick = onSearchClick,
        onRefreshClick = { viewModel.getRandom() },
        expression = expression,
        bookmarkEntity = expressionCollectionEntity,
        cancelBookmark = { viewModel.cancelBookmark(it, Category.ChineseExpression.model) },
        addBookmark = { viewModel.addBookmark(it, Category.ChineseExpression.model) }
    )
}

@Composable
private fun ChineseExpressionIndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onSearchClick: () -> Unit,
    onRefreshClick: () -> Unit,
    expression: ExpressionEntity?,
    bookmarkEntity: BookmarkEntity?,
    addBookmark: (Int) -> Unit,
    cancelBookmark: (Int) -> Unit
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = stringResource(R.string.chinese_expression),
        actions = {
            IconButton(onClick = onBookmarksClick) {
                Icon(
                    imageVector = Icons.Outlined.Bookmarks,
                    contentDescription = stringResource(R.string.bookmarks)
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
            expression?.let { entity ->
                BottomAppBar(
                    actions = {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            IconButton(
                                onClick = {
                                    if (bookmarkEntity != null) {
                                        cancelBookmark(entity.id)
                                    } else {
                                        addBookmark(entity.id)
                                    }
                                }
                            ) {
                                if (bookmarkEntity != null) {
                                    Icon(
                                        imageVector = Icons.Default.Bookmark,
                                        contentDescription = stringResource(R.string.cancel_bookmark),
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.BookmarkBorder,
                                        contentDescription = stringResource(R.string.add_bookmark)
                                    )
                                }
                            }
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = onRefreshClick) {
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
                            onRefreshClick()
                        } else if (velocity > 0 && abs(velocity) > 500f) {
                            onRefreshClick()
                        }
                    }
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                expression?.let { entity ->
                    ExpressionPanel(entity = entity)
                }
            }
        }
    }
}