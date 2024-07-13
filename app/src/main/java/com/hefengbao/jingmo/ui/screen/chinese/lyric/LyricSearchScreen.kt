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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.LyricEntity
import com.hefengbao.jingmo.ui.component.SimpleSearchScaffold

@Composable
fun LyricSearchRoute(
    viewModel: LyricSearchViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
) {
    val lyricEntityCollections by viewModel.lyricEntityCollections.collectAsState(initial = emptyList())

    LyricSearchScreen(
        onBackClick = onBackClick,
        onItemClick = onItemClick,
        onSearch = { viewModel.onQueryChange(it) },
        items = lyricEntityCollections
    )
}

@Composable
private fun LyricSearchScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    onSearch: (String) -> Unit,
    items: List<LyricEntity>
) {
    var query by rememberSaveable {
        mutableStateOf("")
    }

    SimpleSearchScaffold(
        onBackClick = onBackClick,
        query = query,
        onQueryChange = {
            query = it
            onSearch(query)
        },
        onSearch = onSearch
    ) {
        LazyColumn {
            items(
                items = items
            ) { entity ->
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