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
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.WritingCollectionEntity
import com.hefengbao.jingmo.data.database.entity.WritingEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.classicalliterature.writing.components.WritingShowPanel
import kotlinx.serialization.json.Json

@Composable
fun WritingShowRoute(
    viewModel: WritingShowViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit
) {

    val writing by viewModel.writing.collectAsState()
    val writingCollectionEntity by viewModel.writingCollectionEntity.collectAsState()

    WritingShowScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        writing = writing,
        writingCollectionEntity = writingCollectionEntity,
        setCollect = {
            viewModel.setCollect(it)
        },
        setUncollect = {
            viewModel.setUncollect(it)
        },
        json = viewModel.json
    )
}

@Composable
private fun WritingShowScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    writing: WritingEntity?,
    writingCollectionEntity: WritingCollectionEntity?,
    setCollect: (Int) -> Unit,
    setUncollect: (Int) -> Unit,
    json: Json
) {
    writing?.let {
        SimpleScaffold(
            onBackClick = onBackClick,
            title = "诗文",
            actions = {
                IconButton(onClick = { onCaptureClick(it.id) }) {
                    Icon(imageVector = Icons.Default.Photo, contentDescription = null)
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
                                    if (writingCollectionEntity != null) {
                                        setUncollect(writing.id)
                                    } else {
                                        setCollect(writing.id)
                                    }
                                }
                            ) {
                                if (writingCollectionEntity != null) {
                                    Icon(
                                        imageVector = Icons.Default.Bookmark,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.BookmarkBorder,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    },
                )
            }
        ) {
            WritingShowPanel(
                writing = it,
                json = json
            )
        }
    }
}