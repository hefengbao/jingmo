package com.hefengbao.jingmo.ui.screen.poem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.hefengbao.jingmo.data.database.model.SimpleWritingInfo
import com.hefengbao.jingmo.ui.component.SimpleSearchScaffold

@Composable
fun PoemSearchRoute(
    viewModel: PoemSearchViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (id: String, type: String, query: String) -> Unit,
) {
    val writings = viewModel.writings.collectAsLazyPagingItems()

    PoemSearchScreen(
        onBackClick = onBackClick,
        writings = writings,
        onItemClick = onItemClick,
        type = viewModel.type,
        onSearch = {
            viewModel.search(it)
        }
    )
}

@Composable
private fun PoemSearchScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    writings: LazyPagingItems<SimpleWritingInfo>,
    onItemClick: (id: String, type: String, query: String) -> Unit,
    type: String,
    onSearch: (String) -> Unit,
) {
    var query by rememberSaveable {
        mutableStateOf("")
    }
    SimpleSearchScaffold(
        onBackClick = onBackClick,
        value = query,
        onValueChange = {
            query = it
            onSearch(it)
        },
        onSearch = { /*TODO*/ }
    ) {
        LazyColumn {
            items(
                items = writings
            ) {
                it?.let { entity ->
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .clickable {
                                onItemClick(entity.id.toString(), type, query)
                            }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
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