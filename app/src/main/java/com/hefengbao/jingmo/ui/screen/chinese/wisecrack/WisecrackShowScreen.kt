/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.wisecrack

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.outlined.Photo
import androidx.compose.material3.FloatingActionButton
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
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackEntity
import com.hefengbao.jingmo.data.enums.Category
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinese.wisecrack.components.ChineseWisecrackPanel

@Composable
fun ChineseWisecrackShowRoute(
    viewModel: WisecrackShowViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
) {

    val wisecrackEntity by viewModel.wisecrackEntity.collectAsState()
    val bookmarkEntity by viewModel.bookmarkEntity.collectAsState(null)

    ChineseWisecrackShowScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        wisecrackEntity = wisecrackEntity,
        bookmarkEntity = bookmarkEntity,
        cancelBookmark = { viewModel.cancelBookmark(it, Category.ChineseWisecrack.model) },
        addBookmark = { viewModel.addBookmark(it, Category.ChineseWisecrack.model) },
        isBookmarked = { viewModel.isBookmarked(it, Category.ChineseWisecrack.model) }
    )
}

@Composable
private fun ChineseWisecrackShowScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    wisecrackEntity: WisecrackEntity?,
    bookmarkEntity: BookmarkEntity?,
    cancelBookmark: (Int) -> Unit,
    addBookmark: (Int) -> Unit,
    isBookmarked: (Int) -> Unit
) {
    wisecrackEntity?.let { entity ->
        LaunchedEffect(entity) { isBookmarked(entity.id) }
        SimpleScaffold(
            onBackClick = onBackClick,
            title = stringResource(R.string.chinese_wisecrack),
            actions = {
                IconButton(onClick = { onCaptureClick(entity.id) }) {
                    Icon(
                        imageVector = Icons.Outlined.Photo,
                        contentDescription = stringResource(R.string.capture)
                    )
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        if (bookmarkEntity == null) {
                            addBookmark(entity.id)
                        } else {
                            cancelBookmark(entity.id)
                        }
                    }
                ) {
                    if (bookmarkEntity == null) {
                        Icon(
                            imageVector = Icons.Default.BookmarkBorder,
                            contentDescription = stringResource(R.string.add_bookmark)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Bookmark,
                            contentDescription = stringResource(R.string.cancel_bookmark),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        ) {
            ChineseWisecrackPanel(
                entity = entity,
            )
        }
    }
}