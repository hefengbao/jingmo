/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem

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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemEntity
import com.hefengbao.jingmo.data.enums.Category
import com.hefengbao.jingmo.data.model.IdTitle
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem.components.ClassicPoemPanel
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
fun ClassicPoemReadRoute(
    viewModel: ClassicPoemReadViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val classicPoemEntity by viewModel.classicPoemEntity.collectAsState()
    val prevId by viewModel.prevId.collectAsState()
    val nextId by viewModel.nextId.collectAsState()
    val bookmarkEntity by viewModel.bookmarkEntity.collectAsState(null)
    val idTitles = viewModel.idTitles.collectAsLazyPagingItems()

    classicPoemEntity?.let { entity ->
        LaunchedEffect(entity) {
            viewModel.isBookmarked(entity.id, Category.ClassicalLiteratureClassicPoem.model)
        }
    }

    ClassicPoemReadScreen(
        onBackClick = onBackClick,
        setCurrentId = { viewModel.setCurrentId(it) },
        addBookmark = { viewModel.addBookmark(it, Category.ClassicalLiteratureClassicPoem.model) },
        cancelBookmark = {
            viewModel.cancelBookmark(
                it,
                Category.ClassicalLiteratureClassicPoem.model
            )
        },
        setLastReadId = { viewModel.setLastReadId(it) },
        classicPoemEntity = classicPoemEntity,
        prevId = prevId,
        nextId = nextId,
        bookmarkEntity = bookmarkEntity,
        idTitles = idTitles,
        setQuery = viewModel::setQuery
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ClassicPoemReadScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    setCurrentId: (Int) -> Unit,
    addBookmark: (Int) -> Unit,
    cancelBookmark: (Int) -> Unit,
    setLastReadId: (Int) -> Unit,
    classicPoemEntity: ClassicPoemEntity?,
    prevId: Int?,
    nextId: Int?,
    bookmarkEntity: BookmarkEntity?,
    idTitles: LazyPagingItems<IdTitle>,
    setQuery: (String) -> Unit,
) {
    val annotationSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showAnnotationBottomSheet by remember { mutableStateOf(false) }
    val translationSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showTranslationBottomSheet by remember { mutableStateOf(false) }
    val poemSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    var showPoemBottomSheet by remember { mutableStateOf(false) }
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var showIndexBottomSheet by remember { mutableStateOf(false) }
    var query by rememberSaveable { mutableStateOf("") }

    classicPoemEntity?.let { entity ->
        LaunchedEffect(entity) {
            setLastReadId(entity.id)
        }
        LaunchedEffect(entity) {
            scope.launch {
                state.animateScrollToItem(0)
            }
        }
        SimpleScaffold(
            onBackClick = onBackClick,
            title = entity.title,
            actions = {
                IconButton(
                    onClick = { showIndexBottomSheet = true }
                ) {
                    Icon(imageVector = Icons.Outlined.Menu, contentDescription = "目录")
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
                            onClick = { prevId?.let(setCurrentId) },
                            enabled = prevId != null
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = stringResource(R.string.previous)
                            )
                        }
                        OutlinedButton(
                            onClick = {
                                showAnnotationBottomSheet = true
                            },
                            enabled = classicPoemEntity.annotation != null
                        ) {
                            Text(text = "注")
                        }
                        OutlinedButton(
                            onClick = {
                                showTranslationBottomSheet = true
                            },
                            enabled = classicPoemEntity.translation != null
                        ) {
                            Text(text = "译")
                        }
                        OutlinedButton(onClick = { showPoemBottomSheet = true }) {
                            Text(text = "文")
                        }
                        if (bookmarkEntity == null) {
                            IconButton(onClick = { addBookmark(entity.id) }) {
                                Icon(
                                    imageVector = Icons.Default.BookmarkBorder,
                                    contentDescription = stringResource(R.string.add_bookmark)
                                )
                            }
                        } else {
                            IconButton(onClick = { cancelBookmark(entity.id) }) {
                                Icon(
                                    imageVector = Icons.Default.Bookmark,
                                    contentDescription = stringResource(R.string.cancel_bookmark),
                                    tint = MaterialTheme.colorScheme.primary
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
                                nextId?.let(setCurrentId)
                            } else if (velocity > 0 && abs(velocity) > 500f) {
                                prevId?.let(setCurrentId)
                            }
                        }
                    )
            ) {
                ClassicPoemPanel(
                    entity = entity,
                    state = state,
                    showAnnotationBottomSheet = showAnnotationBottomSheet,
                    annotationSheetState = annotationSheetState,
                    onAnnotationBottomSheetDismiss = { showAnnotationBottomSheet = false },
                    showTranslationBottomSheet = showTranslationBottomSheet,
                    translationSheetState = translationSheetState,
                    onTranslationBottomSheetDismiss = { showTranslationBottomSheet = false },
                    showPoemBottomSheet = showPoemBottomSheet,
                    poemSheetState = poemSheetState,
                    onPoemBottomSheetDismiss = { showPoemBottomSheet = false }
                )

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
}