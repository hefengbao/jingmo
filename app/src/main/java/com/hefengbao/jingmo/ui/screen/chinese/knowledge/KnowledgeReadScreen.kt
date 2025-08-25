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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.BottomAppBar
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
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeEntity
import com.hefengbao.jingmo.data.enums.Category
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinese.knowledge.components.KnowledgePanel
import kotlin.math.abs

@Composable
fun ChineseKnowledgeReadRoute(
    viewModel: KnowledgeReadViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val knowledgeEntity by viewModel.knowledgeEntity.collectAsState(initial = null)
    val bookmarkEntity by viewModel.bookmarkEntity.collectAsState(initial = null)
    val nextId by viewModel.nextId.collectAsState()
    val prevId by viewModel.prevId.collectAsState()

    ChineseKnowledgeReadScreen(
        onBackClick = onBackClick,
        knowledgeEntity = knowledgeEntity,
        bookmarkEntity = bookmarkEntity,
        addBookmark = { viewModel.addBookmark(it, Category.ChineseKnowledge.model) },
        cancelBookmark = { viewModel.cancelBookmark(it, Category.ChineseKnowledge.model) },
        isBookmarked = { viewModel.isBookmarked(it, Category.ChineseKnowledge.model) },
        prevId = prevId,
        nextId = nextId,
        setCurrentId = { viewModel.setCurrentId(it) }
    )
}

@Composable
private fun ChineseKnowledgeReadScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    knowledgeEntity: KnowledgeEntity?,
    bookmarkEntity: BookmarkEntity?,
    addBookmark: (Int) -> Unit,
    cancelBookmark: (Int) -> Unit,
    isBookmarked: (Int) -> Unit,
    nextId: Int?,
    prevId: Int?,
    setCurrentId: (Int) -> Unit,
) {
    val state = rememberScrollState()
    knowledgeEntity?.let { entity ->
        LaunchedEffect(entity) { isBookmarked(entity.id) }
        SimpleScaffold(
            onBackClick = onBackClick,
            title = stringResource(R.string.chinese_knowledge),
            bottomBar = {
                BottomAppBar(
                    actions = {
                        Row(
                            modifier = modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(
                                onClick = { prevId?.let(setCurrentId) },
                                enabled = prevId != null
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                    contentDescription = stringResource(R.string.previous)
                                )
                            }
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
                            IconButton(
                                onClick = { nextId?.let(setCurrentId) },
                                enabled = nextId != null
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Default.ArrowForward,
                                    contentDescription = stringResource(R.string.next)
                                )
                            }
                        }
                    },
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
                        onDragStopped = {
                            if (it < 0 && abs(it) > 500f) {
                                nextId?.let(setCurrentId)
                            } else if (it > 0 && abs(it) > 500f) {
                                prevId?.let(setCurrentId)
                            }
                        }
                    )
            ) {
                KnowledgePanel(entity = entity, state = state)
            }
        }
    }
}