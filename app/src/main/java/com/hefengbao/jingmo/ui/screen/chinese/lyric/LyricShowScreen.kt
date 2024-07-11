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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.LyricCollectionEntity
import com.hefengbao.jingmo.data.database.entity.LyricEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinese.lyric.components.LyricShowPanel

@Composable
fun LyricShowRoute(
    viewModel: LyricShowViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val lyricEntity by viewModel.lyricEntity.collectAsState(initial = null)
    val lyricCollectionEntity by viewModel.lyricCollectionEntity.collectAsState(initial = null)

    LyricShowScreen(
        onBackClick = onBackClick,
        lyricEntity = lyricEntity,
        lyricCollectionEntity = lyricCollectionEntity,
        setCollect = { viewModel.collect(it) },
        setUncollect = { viewModel.uncollect(it) }
    )
}

@Composable
private fun LyricShowScreen(
    onBackClick: () -> Unit,
    lyricEntity: LyricEntity?,
    lyricCollectionEntity: LyricCollectionEntity?,
    setCollect: (Int) -> Unit,
    setUncollect: (Int) -> Unit,
) {
    lyricEntity?.let { entity ->
        SimpleScaffold(
            onBackClick = onBackClick,
            title = "歌词",
            floatingActionButton = {
                if (lyricCollectionEntity == null) {
                    FloatingActionButton(onClick = { setCollect(entity.id) }) {
                        Icon(
                            imageVector = Icons.Default.BookmarkBorder,
                            contentDescription = null
                        )
                    }
                } else {
                    FloatingActionButton(onClick = { setUncollect(entity.id) }) {
                        Icon(
                            imageVector = Icons.Default.Bookmark,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        ) {
            LyricShowPanel(entity = entity)
        }
    }
}