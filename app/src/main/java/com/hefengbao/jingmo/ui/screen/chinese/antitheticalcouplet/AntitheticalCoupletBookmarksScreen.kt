/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.hefengbao.jingmo.data.database.entity.chinese.AntitheticalCoupletEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun AntitheticalCoupletBookmarksRoute(
    viewModel: AntitheticalCoupletBookmarksViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
) {
    val pagingItems = viewModel.items.collectAsLazyPagingItems()

    AntitheticalCoupletBookmarksScreen(
        onBackClick = onBackClick,
        pagingItems = pagingItems,
        onItemClick = onItemClick
    )
}

@Composable
private fun AntitheticalCoupletBookmarksScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    pagingItems: LazyPagingItems<AntitheticalCoupletEntity>,
    onItemClick: (Int) -> Unit,
) {
    SimpleScaffold(onBackClick = onBackClick, title = "收藏列表") {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = pagingItems
            ) {
                it?.let { entity ->
                    Card(onClick = { onItemClick(entity.id) }) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            text = entity.body,
                        )
                    }
                }
            }
        }
    }
}