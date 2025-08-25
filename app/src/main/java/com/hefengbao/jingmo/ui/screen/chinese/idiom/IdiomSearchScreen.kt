/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.idiom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.hefengbao.jingmo.data.database.entity.chinese.IdiomEntity
import com.hefengbao.jingmo.ui.component.SimpleSearchScaffold

@Composable
fun IdiomSearchRoute(
    modifier: Modifier = Modifier,
    viewModel: IdiomSearchViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
) {
    val idiomEntities = viewModel.idiomEntities.collectAsLazyPagingItems()

    IdiomSearchScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onItemClick = onItemClick,
        idiomEntities = idiomEntities,
        onSearch = { viewModel.search(it) },
    )
}

@Composable
private fun IdiomSearchScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    idiomEntities: LazyPagingItems<IdiomEntity>,
    onSearch: (String) -> Unit,
) {
    var query by rememberSaveable {
        mutableStateOf("")
    }

    SimpleSearchScaffold(
        onBackClick = onBackClick,
        query = query,
        onQueryChange = {
            query = it
            if (query.isNotEmpty()) {
                onSearch(query)
            }
        },
        onSearch = onSearch
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth(),
            state = rememberLazyListState()
        ) {
            items(
                count = idiomEntities.itemCount
            ) {
                idiomEntities[it]?.let { entity ->
                    Text(
                        modifier = modifier
                            .fillMaxWidth()
                            .clickable {
                                onItemClick(entity.id)
                            }
                            .padding(16.dp),
                        text = entity.word,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}