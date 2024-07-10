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
import androidx.paging.compose.items
import com.hefengbao.jingmo.data.database.model.SimpleIdiomInfo
import com.hefengbao.jingmo.ui.component.SimpleSearchScaffold

@Composable
fun IdiomSearchRoute(
    modifier: Modifier = Modifier,
    viewModel: IdiomSearchViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
) {
    val idioms = viewModel.idioms.collectAsLazyPagingItems()

    IdiomSearchScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onItemClick = onItemClick,
        idioms = idioms,
        onSearch = { viewModel.search(it) },
    )
}

@Composable
private fun IdiomSearchScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    idioms: LazyPagingItems<SimpleIdiomInfo>,
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
                items = idioms
            ) {
                it?.let { entity ->
                    Text(
                        modifier = modifier
                            .fillMaxWidth()
                            .clickable {
                                onItemClick(it.id)
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