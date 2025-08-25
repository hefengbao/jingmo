/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.knowledge

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun KnowledgeBookmarksRoute(
    viewModel: KnowledgeBookmarksViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    val knowledgeEntities = viewModel.knowledgeEntities.collectAsLazyPagingItems()

    KnowledgeBookmarksScreen(
        onBackClick = onBackClick,
        onItemClick = onItemClick,
        knowledgeEntities = knowledgeEntities
    )
}

@Composable
private fun KnowledgeBookmarksScreen(
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    knowledgeEntities: LazyPagingItems<KnowledgeEntity>
) {
    SimpleScaffold(onBackClick = onBackClick, title = stringResource(R.string.bookmarks)) {
        LazyColumn {
            items(
                count = knowledgeEntities.itemCount
            ) {
                knowledgeEntities[it]?.let { entity ->
                    Card(
                        modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp),
                        onClick = { onItemClick(entity.id) }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(text = entity.content)
                        }
                    }
                }
            }
        }
    }
}