/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.idiom

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.outlined.Photo
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
import com.hefengbao.jingmo.data.database.entity.chinese.IdiomEntity
import com.hefengbao.jingmo.data.enums.Category
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinese.idiom.components.IdiomPanel

@Composable
fun IdiomRoute(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    viewModel: IdiomShowViewModel = hiltViewModel()
) {
    val idiomEntity by viewModel.idiomEntity.collectAsState()
    val bookmarkEntity by viewModel.bookmarkEntity.collectAsState(null)

    IdiomScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        idiomEntity = idiomEntity,
        bookmarkEntity = bookmarkEntity,
        addBookmark = { viewModel.cancelBookmark(it, Category.ChineseIdiom.model) },
        cancelBookmark = { viewModel.addBookmark(it, Category.ChineseIdiom.model) },
        isBookmarked = { viewModel.isBookmarked(it, Category.ChineseIdiom.model) }
    )
}

@Composable
private fun IdiomScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    idiomEntity: IdiomEntity?,
    bookmarkEntity: BookmarkEntity?,
    addBookmark: (Int) -> Unit,
    cancelBookmark: (Int) -> Unit,
    isBookmarked: (Int) -> Unit,
) {

    idiomEntity?.let { entity ->
        LaunchedEffect(entity) { isBookmarked(entity.id) }
        SimpleScaffold(
            onBackClick = onBackClick,
            title = entity.word,
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
                        IconButton(
                            onClick = {
                                if (bookmarkEntity != null) {
                                    addBookmark(entity.id)
                                } else {
                                    cancelBookmark(entity.id)
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
                }
            }
        ) {
            IdiomPanel(idiom = entity)
        }
    }
}