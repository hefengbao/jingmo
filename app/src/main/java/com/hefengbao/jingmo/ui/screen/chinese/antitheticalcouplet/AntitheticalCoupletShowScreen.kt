/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet

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
import com.hefengbao.jingmo.data.database.entity.chinese.AntitheticalCoupletEntity
import com.hefengbao.jingmo.data.enums.Category
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet.components.AntitheticalCoupletPanel

@Composable
fun AntitheticalCoupletShowRoute(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    viewModel: AntitheticalCoupletShowViewModel = hiltViewModel()
) {
    val antitheticalCoupletEntity by viewModel.antitheticalCoupletEntity.collectAsState()
    val bookmarkEntity by viewModel.bookmarkEntity.collectAsState(null)

    AntitheticalCoupletShowScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        antitheticalCoupletEntity = antitheticalCoupletEntity,
        bookmarkEntity = bookmarkEntity,
        cancelBookmark = {
            viewModel.cancelBookmark(
                it,
                Category.ChineseAntitheticalCouplet.model
            )
        },
        addBookmark = { viewModel.addBookmark(it, Category.ChineseAntitheticalCouplet.model) },
        isBookmarked = { viewModel.isBookmarked(it, Category.ChineseAntitheticalCouplet.model) }
    )
}

@Composable
private fun AntitheticalCoupletShowScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    antitheticalCoupletEntity: AntitheticalCoupletEntity?,
    bookmarkEntity: BookmarkEntity?,
    cancelBookmark: (Int) -> Unit,
    addBookmark: (Int) -> Unit,
    isBookmarked: (Int) -> Unit,
) {

    antitheticalCoupletEntity?.let { entity ->
        LaunchedEffect(entity) {
            isBookmarked(entity.id)
        }
        SimpleScaffold(
            onBackClick = onBackClick,
            title = stringResource(R.string.chinese_antitheticalcouplet),
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
                                    cancelBookmark(entity.id)
                                } else {
                                    addBookmark(entity.id)
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
            AntitheticalCoupletPanel(antitheticalCouplet = antitheticalCoupletEntity)
        }
    }
}