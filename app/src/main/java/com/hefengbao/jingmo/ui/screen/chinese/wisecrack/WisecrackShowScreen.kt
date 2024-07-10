/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.wisecrack

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
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackCollectionEntity
import com.hefengbao.jingmo.data.database.model.ChineseWisecrackWithBookmark
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinese.wisecrack.components.ChineseWisecrackShowPanel

@Composable
fun ChineseWisecrackShowRoute(
    viewModel: WisecrackShowViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
) {

    val chineseWisecrack by viewModel.wisecrack.collectAsState()
    val chineseWisecrackCollectionEntity by viewModel.chineseWisecrackCollectionEntity.collectAsState()

    ChineseWisecrackShowScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        chineseCrack = chineseWisecrack,
        chineseWisecrackCollectionEntity = chineseWisecrackCollectionEntity,
        setUncollect = { viewModel.setUncollect(it) },
        setCollect = { viewModel.setCollect(it) },
    )
}

@Composable
private fun ChineseWisecrackShowScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    chineseCrack: ChineseWisecrackWithBookmark?,
    chineseWisecrackCollectionEntity: ChineseWisecrackCollectionEntity?,
    setUncollect: (Int) -> Unit,
    setCollect: (Int) -> Unit,
) {
    chineseCrack?.let { entity ->
        SimpleScaffold(
            onBackClick = onBackClick,
            title = "歇后语",
            actions = {
                IconButton(onClick = { onCaptureClick(entity.id) }) {
                    Icon(imageVector = Icons.Default.Photo, contentDescription = null)
                }
            },
            bottomBar = {
                BottomAppBar {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        if (chineseWisecrackCollectionEntity == null) {
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
                    }
                }
            }
        ) {
            ChineseWisecrackShowPanel(
                entity = entity,
            )
        }
    }
}