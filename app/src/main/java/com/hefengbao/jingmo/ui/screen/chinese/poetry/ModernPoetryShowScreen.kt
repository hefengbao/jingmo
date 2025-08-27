/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.poetry

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.outlined.Photo
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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

@Composable
fun ModernPoetryShowRoute(
    viewModel: ModernPoetryShowViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
) {
    val poetryEntity by viewModel.poetryEntity.collectAsState(initial = null)
    val bookmarkEntity by viewModel.bookmarkEntity.collectAsState(initial = null)

    ModernPoetryShowScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        poetryEntity = poetryEntity,
        bookmarkEntity = bookmarkEntity,
        addBookmark = { viewModel.addBookmark(it, Category.ChineseModernPoetry.model) },
        cancelBookmark = { viewModel.cancelBookmark(it, Category.ChineseModernPoetry.model) },
        isBookmarked = { viewModel.isBookmarked(it, Category.ChineseModernPoetry.model) }
    )
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun ModernPoetryShowScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    poetryEntity: ModernPoetryEntity?,
    bookmarkEntity: BookmarkEntity?,
    addBookmark: (Int) -> Unit,
    cancelBookmark: (Int) -> Unit,
    isBookmarked: (Int) -> Unit,
) {
    val state = rememberScrollState()

    val fabVisible by remember { derivedStateOf { state.value != state.maxValue } }

    poetryEntity?.let { entity ->
        LaunchedEffect(entity) { isBookmarked(entity.id) }
        LaunchedEffect(entity) {
            state.animateScrollTo(0)
        }

        SimpleScaffold(
            onBackClick = onBackClick,
            title = stringResource(R.string.chinese_modernpoetry),
            actions = {
                IconButton(onClick = { onCaptureClick(entity.id) }) {
                    Icon(
                        imageVector = Icons.Outlined.Photo,
                        contentDescription = stringResource(R.string.capture)
                    )
                }
            },
            bottomBar = {
                BottomAppBar {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
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
            },
        ) {
            ModernPoetryPanel(entity = entity, state = state)
        }
    }
}