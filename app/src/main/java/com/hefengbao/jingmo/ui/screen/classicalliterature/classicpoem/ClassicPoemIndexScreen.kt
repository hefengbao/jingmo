/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ReadMore
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.data.database.entity.BookmarkEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemEntity
import com.hefengbao.jingmo.data.enums.Category
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem.components.ClassicPoemPanel
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
fun ClassicPoemIndexRoute(
    viewModel: ClassicPoemIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    onSearchClick: () -> Unit,
) {
    val classicPoemEntity by viewModel.classicPoemEntity.collectAsState(initial = null)
    val bookmarkEntity by viewModel.bookmarkEntity.collectAsState(initial = null)

    LaunchedEffect(classicPoemEntity) {
        classicPoemEntity?.let {
            viewModel.isBookmarked(it.id, Category.ClassicalLiteratureClassicPoem.model)
        }
    }

    ClassicPoemIndexScreen(
        onBackClick = onBackClick,
        onBookmarksClick = onBookmarksClick,
        onReadMoreClick = onReadMoreClick,
        onSearchClick = onSearchClick,
        classicPoemEntity = classicPoemEntity,
        bookmarkEntity = bookmarkEntity,
        onRefresh = { viewModel.getRandom() },
        addBookmark = { viewModel.addBookmark(it, Category.ClassicalLiteratureClassicPoem.model) },
        cancelBookmark = {
            viewModel.cancelBookmark(
                it,
                Category.ClassicalLiteratureClassicPoem.model
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ClassicPoemIndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    onSearchClick: () -> Unit,
    classicPoemEntity: ClassicPoemEntity?,
    bookmarkEntity: BookmarkEntity?,
    onRefresh: () -> Unit,
    addBookmark: (Int) -> Unit,
    cancelBookmark: (Int) -> Unit
) {
    val annotationSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showAnnotationBottomSheet by remember { mutableStateOf(false) }
    val translationSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showTranslationBottomSheet by remember { mutableStateOf(false) }
    val poemSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    var showPoemBottomSheet by remember { mutableStateOf(false) }
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()

    SimpleScaffold(
        onBackClick = onBackClick,
        title = stringResource(R.string.classicalliterature_classicpoem),
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
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search)
                )
            }
        },
        bottomBar = {
            classicPoemEntity?.let {
                BottomAppBar(
                    actions = {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
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
                            if (bookmarkEntity != null) {
                                IconButton(onClick = { cancelBookmark(classicPoemEntity.id) }) {
                                    Icon(
                                        imageVector = Icons.Default.Bookmark,
                                        contentDescription = stringResource(R.string.cancel_bookmark),
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            } else {
                                IconButton(onClick = { addBookmark(classicPoemEntity.id) }) {
                                    Icon(
                                        imageVector = Icons.Default.BookmarkBorder,
                                        contentDescription = stringResource(R.string.add_bookmark)
                                    )
                                }
                            }
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                onRefresh()
                                scope.launch { state.animateScrollToItem(0) }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = stringResource(R.string.refresh)
                            )
                        }
                    }
                )
            }
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
                            onRefresh()
                        } else if (velocity > 0 && abs(velocity) > 500f) {
                            onRefresh()
                        }
                    }
                )
        ) {
            classicPoemEntity?.let { entity ->
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
            }
        }
    }
}