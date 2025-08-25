/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.lyric

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.outlined.Photo
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.data.database.entity.BookmarkEntity
import com.hefengbao.jingmo.data.database.entity.chinese.LyricEntity
import com.hefengbao.jingmo.data.enums.Category
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinese.lyric.components.LyricPanel

@Composable
fun LyricShowRoute(
    viewModel: LyricShowViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit
) {
    val lyricEntity by viewModel.lyricEntity.collectAsState(initial = null)
    val bookmarkEntity by viewModel.bookmarkEntity.collectAsState(initial = null)

    LyricShowScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        lyricEntity = lyricEntity,
        bookmarkEntity = bookmarkEntity,
        addBookmark = { viewModel.addBookmark(it, Category.ChineseLyric.model) },
        cancelBookmark = { viewModel.cancelBookmark(it, Category.ChineseLyric.model) },
        isBookmarked = { viewModel.isBookmarked(it, Category.ChineseLyric.model) }
    )
}

@Composable
private fun LyricShowScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    lyricEntity: LyricEntity?,
    bookmarkEntity: BookmarkEntity?,
    addBookmark: (Int) -> Unit,
    cancelBookmark: (Int) -> Unit,
    isBookmarked: (Int) -> Unit
) {
    lyricEntity?.let { entity ->
        LaunchedEffect(entity) { isBookmarked(entity.id) }
        SimpleScaffold(
            onBackClick = onBackClick,
            title = stringResource(R.string.chinese_lyric),
            actions = {
                IconButton(
                    onClick = { onCaptureClick(entity.id) }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Photo,
                        contentDescription = stringResource(R.string.capture)
                    )
                }
            },
            floatingActionButton = {
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
            },
        ) {
            LyricPanel(entity = entity)
        }
    }
}