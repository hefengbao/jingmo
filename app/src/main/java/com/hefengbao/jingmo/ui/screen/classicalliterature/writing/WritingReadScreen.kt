/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.writing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Photo
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.data.database.entity.BookmarkEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingEntity
import com.hefengbao.jingmo.data.enums.Category
import com.hefengbao.jingmo.data.model.IdTitle
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.classicalliterature.writing.components.WritingPanel
import kotlinx.serialization.json.Json
import kotlin.math.abs

@Composable
fun WritingReadRoute(
    viewModel: WritingReadViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
) {
    val writingEntity by viewModel.writingEntity.collectAsState()
    val bookmarkEntity by viewModel.bookmarkEntity.collectAsState(null)
    val prevId by viewModel.prevId.collectAsState()
    val nextId by viewModel.nextId.collectAsState()
    val idTitles = viewModel.idTitles.collectAsLazyPagingItems()

    writingEntity?.let { entity ->
        LaunchedEffect(entity) {
            viewModel.isBookmarked(entity.id, Category.ClassicalLiteratureWriting.model)
        }
    }

    WritingReadScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        writingEntity = writingEntity,
        bookmarkEntity = bookmarkEntity,
        prevId = prevId,
        nextId = nextId,
        setCurrentId = { viewModel.setCurrentId(it) },
        addBookmark = { viewModel.addBookmark(it, Category.ClassicalLiteratureWriting.model) },
        cancelBookmark = {
            viewModel.cancelBookmark(
                it,
                Category.ClassicalLiteratureWriting.model
            )
        },
        setLastReadId = { viewModel.setLastReadId(it) },
        json = viewModel.json,
        idTitles = idTitles,
        setQuery = viewModel::setQuery
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WritingReadScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    writingEntity: WritingEntity?,
    bookmarkEntity: BookmarkEntity?,
    prevId: Int?,
    nextId: Int?,
    setCurrentId: (Int) -> Unit,
    addBookmark: (Int) -> Unit,
    cancelBookmark: (Int) -> Unit,
    setLastReadId: (Int) -> Unit,
    json: Json,
    idTitles: LazyPagingItems<IdTitle>,
    setQuery: (String) -> Unit,
) {
    var showIndexBottomSheet by remember { mutableStateOf(false) }
    var query by rememberSaveable { mutableStateOf("") }

    writingEntity?.let {
        LaunchedEffect(it) {
            setLastReadId(it.id)
        }
        SimpleScaffold(
            onBackClick = onBackClick,
            title = stringResource(R.string.classicalliterature_writing),
            actions = {
                IconButton(onClick = { onCaptureClick(it.id) }) {
                    Icon(
                        imageVector = Icons.Outlined.Photo,
                        contentDescription = stringResource(R.string.capture)
                    )
                }
                IconButton(onClick = { showIndexBottomSheet = true }) {
                    Icon(imageVector = Icons.Outlined.Menu, contentDescription = "目录")
                }
            },
            bottomBar = {
                BottomAppBar(
                    actions = {
                        Row(
                            modifier = modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = {
                                    setCurrentId(prevId!!)
                                },
                                enabled = prevId != null
                            ) {
                                Icon(
                                    modifier = modifier.padding(8.dp),
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = stringResource(R.string.previous)
                                )
                            }
                            IconButton(
                                onClick = {
                                    if (bookmarkEntity != null) {
                                        cancelBookmark(writingEntity.id)
                                    } else {
                                        addBookmark(writingEntity.id)
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
                            IconButton(
                                modifier = modifier.padding(8.dp),
                                onClick = {
                                    setCurrentId(nextId!!)
                                },
                                enabled = nextId != null
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = stringResource(R.string.next)
                                )
                            }
                        }
                    },
                )
            },
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
                                nextId?.let {
                                    setCurrentId(nextId)
                                }
                            } else if (velocity > 0 && abs(velocity) > 500f) {
                                prevId?.let {
                                    setCurrentId(prevId)
                                }
                            }
                        }
                    )
            ) {
                WritingPanel(
                    writing = it,
                    json = json
                )
            }

            if (showIndexBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showIndexBottomSheet = false },
                ) {
                    LazyColumn {
                        item {
                            SearchBar(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                inputField = {
                                    SearchBarDefaults.InputField(
                                        query = query,
                                        onQueryChange = {
                                            query = it
                                            setQuery(it)
                                        },
                                        onSearch = {},
                                        onExpandedChange = {},
                                        expanded = false
                                    )
                                },
                                expanded = false,
                                onExpandedChange = {},
                                content = {}
                            )
                        }

                        items(
                            count = idTitles.itemCount
                        ) { index ->
                            val idTitle = idTitles[index]

                            idTitle?.let {
                                Text(
                                    text = idTitle.title,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            setCurrentId(idTitle.id)
                                            showIndexBottomSheet = false
                                        }
                                        .padding(16.dp, 8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}