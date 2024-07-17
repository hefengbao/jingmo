/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.idiom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.IdiomCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.IdiomEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinese.idiom.components.IdiomPanel

@Composable
fun IdiomRoute(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    viewModel: IdiomShowViewModel = hiltViewModel()
) {
    val idiom by viewModel.idiom.collectAsState()
    val idiomCollectionEntity by viewModel.idiomCollectionEntity.collectAsState()

    IdiomScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        idiom = idiom,
        idiomCollectionEntity = idiomCollectionEntity,
        setUncollect = { viewModel.setUncollect(it) },
        setCollect = { viewModel.setCollect(it) }
    )
}

@Composable
private fun IdiomScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    idiom: IdiomEntity?,
    idiomCollectionEntity: IdiomCollectionEntity?,
    setUncollect: (Int) -> Unit,
    setCollect: (Int) -> Unit
) {

    idiom?.let { entity ->
        SimpleScaffold(
            onBackClick = onBackClick,
            title = entity.word,
            actions = {
                IconButton(
                    onClick = {
                        if (idiomCollectionEntity != null) {
                            setUncollect(entity.id)
                        } else {
                            setCollect(entity.id)
                        }
                    }
                ) {
                    if (idiomCollectionEntity != null) {
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
                IconButton(onClick = { onCaptureClick(entity.id) }) {
                    Icon(imageVector = Icons.Default.Photo, contentDescription = null)
                }
            }
        ) {
            IdiomPanel(idiom = entity)
        }
    }
}