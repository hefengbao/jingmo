/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.sentence

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.hefengbao.jingmo.data.database.entity.classicalliterature.SentenceEntity
import com.hefengbao.jingmo.ui.component.SimpleSearchScaffold

@Composable
fun SentenceSearchRoute(
    viewModel: SentenceSearchViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCardClick: (Int) -> Unit,
) {
    val sentences = viewModel.sentences.collectAsLazyPagingItems()

    SentenceSearchScreen(
        onBackClick = onBackClick,
        sentences = sentences,
        onSearch = { viewModel.search(it) },
        onCardClick = onCardClick
    )
}

@Composable
private fun SentenceSearchScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    sentences: LazyPagingItems<SentenceEntity>,
    onSearch: (String) -> Unit,
    onCardClick: (Int) -> Unit,
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
            modifier = modifier.fillMaxWidth()
        ) {
            items(
                items = sentences
            ) {
                it?.let { entity ->
                    Card(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .clickable {
                                    onCardClick(entity.id)
                                }
                                .padding(32.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = entity.content,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = entity.from,
                                fontStyle = FontStyle.Italic
                            )
                        }
                    }
                }
            }
        }
    }
}