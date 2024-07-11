/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.lyric

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.hefengbao.jingmo.data.database.entity.LyricEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun LyricBookmarksRoute(
    viewModel: LyricBookmarksViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    val lyricEntityCollections = viewModel.lyricEntityCollections.collectAsLazyPagingItems()

    LyricBookmarksScreen(
        onBackClick = onBackClick,
        onItemClick = onItemClick,
        items = lyricEntityCollections
    )
}

@Composable
private fun LyricBookmarksScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    items: LazyPagingItems<LyricEntity>
) {
    SimpleScaffold(onBackClick = onBackClick, title = "收藏") {
        LazyColumn {
            itemsIndexed(
                items = items
            ) { _: Int, entity: LyricEntity? ->
                entity?.let {
                    var text = ""
                    if (entity.writer != null) {
                        text += "填词：${entity.writer}"
                    }
                    if (entity.singer != null) {
                        if (text.isNotEmpty()) {
                            text += " / "
                        }
                        text += "演唱：${entity.singer}"
                    }

                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .clickable {
                                onItemClick(entity.id)
                            }
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = entity.title)
                        Text(
                            text = text,
                            style = MaterialTheme.typography.labelMedium.copy(
                                brush = null,
                                alpha = .5f
                            )
                        )
                    }
                }
            }
        }
    }
}