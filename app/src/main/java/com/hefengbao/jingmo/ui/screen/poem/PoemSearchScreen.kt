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
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.component.SimpleSearchScaffold

@Composable
fun PoemSearchRoute(
    viewModel: PoemSearchViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (id: String, type: String, query: String) -> Unit,
) {
    val writings = viewModel.writings.collectAsLazyPagingItems()

    var query: String by rememberSaveable {
        if (viewModel.type == "author") {
            mutableStateOf(viewModel.query)
        } else {
            mutableStateOf("")
        }
    }

    PoemSearchScreen(
        onBackClick = onBackClick,
        writings = writings,
        onItemClick = onItemClick,
        type = viewModel.type,
        query = query,
        onQueryChange = {
            query = it
            viewModel.search(it)
        }
    )
}

@Composable
private fun PoemSearchScreen(
    onBackClick: () -> Unit,
    writings: LazyPagingItems<SimpleWritingInfo>,
    onItemClick: (id: String, type: String, query: String) -> Unit,
    type: String,
    query: String,
    onQueryChange: (String) -> Unit,
) {
    if (type == "author") {
        SimpleScaffold(
            onBackClick = onBackClick,
            title = query
        ) {
            List(writings = writings, onItemClick = onItemClick, type = type, query = query)
        }
    } else {
        SimpleSearchScaffold(
            onBackClick = onBackClick,
            value = query,
            onValueChange = {
                onQueryChange(it)
            },
            onSearch = { /*TODO*/ }
        ) {
            List(writings = writings, onItemClick = onItemClick, type = type, query = query)
        }
    }
}

@Composable
private fun List(
    modifier: Modifier = Modifier,
    writings: LazyPagingItems<SimpleWritingInfo>,
    onItemClick: (id: String, type: String, query: String) -> Unit,
    type: String,
    query: String
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