package com.hefengbao.jingmo.ui.screen.chinesewisecrack

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.hefengbao.jingmo.data.database.model.ChineseWisecrackWithBookmark
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun ChineseWisecrackBookmarksRoute(
    viewModel: ChineseWisecrackBookmarksViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val bookmarks = viewModel.bookmarks.collectAsLazyPagingItems()

    ChineseWisecrackBookmarksScreen(
        onBackClick = onBackClick,
        setUncollect = { viewModel.setUncollect(it) },
        bookmarks = bookmarks
    )
}

@Composable
private fun ChineseWisecrackBookmarksScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    setUncollect: (Int) -> Unit,
    bookmarks: LazyPagingItems<ChineseWisecrackWithBookmark>,
) {
    SimpleScaffold(onBackClick = onBackClick, title = "收藏列表") {
        LazyColumn(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = bookmarks,
            ) { item ->
                item?.let { entity ->
                    Card(
                        modifier = modifier.fillMaxWidth()
                    ) {
                        Column {
                            IconButton(
                                modifier = modifier.padding(horizontal = 16.dp),
                                onClick = { setUncollect(entity.id) }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Bookmark,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
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
}