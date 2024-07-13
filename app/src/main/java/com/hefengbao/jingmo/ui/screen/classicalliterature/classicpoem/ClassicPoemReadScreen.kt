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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemCollectionEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem.components.ClassicPoemPanel
import kotlinx.coroutines.launch

@Composable
fun ClassicPoemReadRoute(
    viewModel: ClassicPoemReadViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val classicPoemEntity by viewModel.classicPoemEntity.collectAsState()
    val prevId by viewModel.prevId.collectAsState()
    val nextId by viewModel.nextId.collectAsState()
    val classicPoemCollectionEntity by viewModel.classicPoemCollectionEntity.collectAsState()

    ClassicPoemReadScreen(
        onBackClick = onBackClick,
        setCurrentId = { viewModel.setCurrentId(it) },
        setCollect = { viewModel.collect(it) },
        setUncollect = { viewModel.uncollect(it) },
        setLastReadId = { viewModel.setLastReadId(it) },
        classicPoemEntity = classicPoemEntity,
        prevId = prevId,
        nextId = nextId,
        classicPoemCollectionEntity = classicPoemCollectionEntity,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ClassicPoemReadScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    setCurrentId: (Int) -> Unit,
    setCollect: (Int) -> Unit,
    setUncollect: (Int) -> Unit,
    setLastReadId: (Int) -> Unit,
    classicPoemEntity: ClassicPoemEntity?,
    prevId: Int?,
    nextId: Int?,
    classicPoemCollectionEntity: ClassicPoemCollectionEntity?
) {
    val annotationSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showAnnotationBottomSheet by remember { mutableStateOf(false) }
    val translationSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showTranslationBottomSheet by remember { mutableStateOf(false) }
    val poemSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    var showPoemBottomSheet by remember { mutableStateOf(false) }
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()

    classicPoemEntity?.let { entity ->
        LaunchedEffect(entity) {
            setLastReadId(entity.id)
            scope.launch {
                state.animateScrollToItem(0)
            }
        }
        SimpleScaffold(
            onBackClick = onBackClick,
            title = entity.title,
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
                        if (classicPoemCollectionEntity == null) {
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