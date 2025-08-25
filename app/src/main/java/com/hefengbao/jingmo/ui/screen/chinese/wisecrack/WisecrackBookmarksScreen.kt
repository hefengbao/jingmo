/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.wisecrack

import androidx.compose.foundation.layout.Arrangement
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
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun ChineseWisecrackBookmarksRoute(
    viewModel: WisecrackBookmarksViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val pagingItems = viewModel.wisecrackEntities.collectAsLazyPagingItems()

    ChineseWisecrackBookmarksScreen(
        onBackClick = onBackClick,
        pagingItems = pagingItems
    )
}

@Composable
private fun ChineseWisecrackBookmarksScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    pagingItems: LazyPagingItems<WisecrackEntity>,
) {
    SimpleScaffold(onBackClick = onBackClick, title = stringResource(R.string.bookmarks)) {
        LazyColumn(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                count = pagingItems.itemCount,
            ) {
                pagingItems[it]?.let { entity ->
                    Card(
                        modifier = modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(text = entity.riddle)
                            Text(text = "一 ${entity.answer}")
                        }
                    }
                }
            }
        }
    }
}