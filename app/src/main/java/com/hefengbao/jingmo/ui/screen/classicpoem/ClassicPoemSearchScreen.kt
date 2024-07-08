/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicpoem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.ClassicPoemEntity
import com.hefengbao.jingmo.ui.component.SimpleSearchScaffold

@Composable
fun ClassicPoemSearchRoute(
    viewModel: ClassicPoemSearchScreenViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    val poems by viewModel.poems.collectAsState(initial = emptyList())

    ClassicPoemSearchScreen(
        onBackClick = onBackClick,
        onSearch = { viewModel.search(it) },
        onItemClick = onItemClick,
        poems = poems
    )
}

@Composable
private fun ClassicPoemSearchScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSearch: (String) -> Unit,
    onItemClick: (Int) -> Unit,
    poems: List<ClassicPoemEntity>
) {
    var query by rememberSaveable {
        mutableStateOf("")
    }

    SimpleSearchScaffold(
        onBackClick = onBackClick,
        query = query,
        onQueryChange = { query = it },
        onSearch = onSearch,
    ) {
        LazyColumn {
            itemsIndexed(
                items = poems
            ) { index: Int, item: ClassicPoemEntity ->
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable {
                            onItemClick(item.id)
                        }
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = item.title)
                    Text(
                        text = "${item.dynasty}·${item.writer}",
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = item.content,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                if (index != poems.lastIndex) {
                    Divider()
                }
            }
        }
    }
}