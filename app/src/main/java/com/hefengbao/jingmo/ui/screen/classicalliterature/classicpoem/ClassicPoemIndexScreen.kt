/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemCollectionEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem.components.ClassicPoemPanel
import kotlinx.coroutines.launch

@Composable
fun ClassicPoemIndexRoute(
    viewModel: ClassicPoemIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    onSearchClick: () -> Unit,
) {
    val classicPoemEntity by viewModel.classicPoemEntity.collectAsState(initial = null)
    val classicPoemCollectionEntity by viewModel.classicPoemCollectionEntity.collectAsState(initial = null)

    LaunchedEffect(classicPoemEntity) {
        classicPoemEntity?.let {
            viewModel.isCollected(it.id)
        }
    }

    ClassicPoemIndexScreen(
        onBackClick = onBackClick,
        onBookmarksClick = onBookmarksClick,
        onReadMoreClick = onReadMoreClick,
        onSearchClick = onSearchClick,
        classicPoemEntity = classicPoemEntity,
        onFabClick = {
            viewModel.getRandom()
        },
        classicPoemCollectionEntity = classicPoemCollectionEntity,
        setCollected = { viewModel.collect(it) },
        setUncollected = { viewModel.uncollect(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ClassicPoemIndexScreen(
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    onSearchClick: () -> Unit,
    classicPoemEntity: ClassicPoemEntity?,
    onFabClick: () -> Unit,
    classicPoemCollectionEntity: ClassicPoemCollectionEntity?,
    setCollected: (Int) -> Unit,
    setUncollected: (Int) -> Unit
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
        title = "经典诗文",
        actions = {
            IconButton(onClick = onBookmarksClick) {
                Icon(imageVector = Icons.Outlined.Bookmarks, contentDescription = "收藏")
            }
            IconButton(onClick = onReadMoreClick) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ReadMore, contentDescription = "阅读")
            }
            IconButton(onClick = onSearchClick) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "搜索")
            }
        },
        bottomBar = {
            classicPoemEntity?.let {
                BottomAppBar(
                    actions = {
                        Row(
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
                            if (classicPoemCollectionEntity != null) {
                                IconButton(onClick = { setUncollected(classicPoemEntity.id) }) {
                                    Icon(
                                        imageVector = Icons.Default.Bookmark,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            } else {
                                IconButton(onClick = { setCollected(classicPoemEntity.id) }) {
                                    Icon(
                                        imageVector = Icons.Default.BookmarkBorder,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                onFabClick()
                                scope.launch { state.animateScrollToItem(0) }
                            }
                        ) {
                            Icon(imageVector = Icons.Default.Refresh, contentDescription = "刷新")
                        }
                    }
                )
            }
        },
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