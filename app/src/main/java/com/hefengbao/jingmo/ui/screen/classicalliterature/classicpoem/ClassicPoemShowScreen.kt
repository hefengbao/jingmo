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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.data.database.entity.BookmarkEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemEntity
import com.hefengbao.jingmo.data.enums.Category
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem.components.ClassicPoemPanel

@Composable
fun ClassicPoemShowRoute(
    viewModel: ClassicPoemShowViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val classicPoemEntity by viewModel.classicPoemEntity.collectAsState()
    val bookmarkEntity by viewModel.bookmarkEntity.collectAsState(null)

    classicPoemEntity?.let { entity ->
        LaunchedEffect(entity) {
            viewModel.isBookmarked(entity.id, Category.ClassicalLiteratureClassicPoem.model)
        }
    }

    ClassicPoemShowScreen(
        onBackClick = onBackClick,
        addBookmark = { viewModel.addBookmark(it, Category.ClassicalLiteratureClassicPoem.model) },
        cancelBookmark = {
            viewModel.cancelBookmark(
                it,
                Category.ClassicalLiteratureClassicPoem.model
            )
        },
        classicPoemEntity = classicPoemEntity,
        bookmarkEntity = bookmarkEntity
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ClassicPoemShowScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    addBookmark: (Int) -> Unit,
    cancelBookmark: (Int) -> Unit,
    classicPoemEntity: ClassicPoemEntity?,
    bookmarkEntity: BookmarkEntity?
) {
    val annotationSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showAnnotationBottomSheet by remember { mutableStateOf(false) }
    val translationSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showTranslationBottomSheet by remember { mutableStateOf(false) }
    val poemSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    var showPoemBottomSheet by remember { mutableStateOf(false) }
    val state = rememberLazyListState()

    classicPoemEntity?.let { entity ->
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