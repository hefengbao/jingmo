package com.hefengbao.jingmo.ui.screen.poem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.hefengbao.jingmo.data.database.model.WritingCollectionInfo
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun PoemCollectionRoute(
    viewModel: PoemCollectionViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (id: Int) -> Unit
) {
    val poems = viewModel.poems.collectAsLazyPagingItems()

    PoemCollectionScreen(
        onBackClick = onBackClick,
        poems = poems,
        onItemClick = onItemClick
    )
}

@Composable
private fun PoemCollectionScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    poems: LazyPagingItems<WritingCollectionInfo>,
    onItemClick: (id: Int) -> Unit
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = "收藏列表"
    ) {
        LazyColumn(
            modifier = modifier.fillMaxWidth()
        ) {
            items(
                items = poems
            ) {
                it?.let { entity ->
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .clickable {
                                onItemClick(it.id)
                            }
                            .padding(16.dp, 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = entity.title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "${entity.type} ${entity.dynasty}·${entity.author}",
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }
        }
    }
}