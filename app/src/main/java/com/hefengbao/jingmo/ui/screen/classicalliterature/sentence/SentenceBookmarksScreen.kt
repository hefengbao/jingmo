/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.sentence

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.data.database.entity.classicalliterature.SentenceEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun SentenceBookmarksRoute(
    viewModel: SentenceBookmarksViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val pagingItems = viewModel.sentenceEntities.collectAsLazyPagingItems()

    SentenceBookmarksScreen(
        onBackClick = onBackClick,
        pagingItems = pagingItems,
    )
}

@Composable
private fun SentenceBookmarksScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    pagingItems: LazyPagingItems<SentenceEntity>,
) {
    SimpleScaffold(onBackClick = onBackClick, title = stringResource(R.string.bookmarks)) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                count = pagingItems.itemCount
            ) {
                pagingItems[it]?.let { entity ->
                    Card(
                        modifier = modifier.fillMaxWidth()
                    ) {
                        Column {
                            Column(
                                modifier = modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = entity.content,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Text(
                                    text = "一 ${entity.from}",
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}