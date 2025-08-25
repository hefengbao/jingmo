/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.writing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.data.database.entity.BookmarkEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingEntity
import com.hefengbao.jingmo.data.enums.Category
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.classicalliterature.writing.components.WritingPanel
import kotlinx.serialization.json.Json

@Composable
fun WritingShowRoute(
    viewModel: WritingShowViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit
) {

    val writingEntity by viewModel.writingEntity.collectAsState()
    val bookmarkEntity by viewModel.bookmarkEntity.collectAsState(null)

    writingEntity?.let { entity ->
        LaunchedEffect(entity) {
            viewModel.isBookmarked(entity.id, Category.ClassicalLiteratureWriting.model)
        }
    }

    WritingShowScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        writingEntity = writingEntity,
        bookmarkEntity = bookmarkEntity,
        addBookmark = { viewModel.addBookmark(it, Category.ClassicalLiteratureWriting.model) },
        cancelBookmark = {
            viewModel.cancelBookmark(
                it,
                Category.ClassicalLiteratureWriting.model
            )
        },
        json = viewModel.json
    )
}

@Composable
private fun WritingShowScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    writingEntity: WritingEntity?,
    bookmarkEntity: BookmarkEntity?,
    addBookmark: (Int) -> Unit,
    cancelBookmark: (Int) -> Unit,
    json: Json
) {
    writingEntity?.let {
        SimpleScaffold(
            onBackClick = onBackClick,
            title = stringResource(R.string.classicalliterature_writing),
            actions = {
                IconButton(onClick = { onCaptureClick(it.id) }) {
                    Icon(imageVector = Icons.Outlined.Photo, contentDescription = null)
                }
            },
            bottomBar = {
                BottomAppBar(
                    actions = {
                        Row(
                            modifier = modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = {
                                    if (bookmarkEntity != null) {
                                        cancelBookmark(writingEntity.id)
                                    } else {
                                        addBookmark(writingEntity.id)
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
                    },
                )
            }
        ) {
            WritingPanel(
                writing = it,
                json = json
            )
        }
    }
}