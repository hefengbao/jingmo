package com.hefengbao.jingmo.ui.screen.idiom

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.hefengbao.jingmo.data.database.model.SimpleIdiomInfo
import com.hefengbao.jingmo.ui.component.SimpleSearchScaffold

@Composable
fun IdiomListRoute(
    modifier: Modifier = Modifier,
    viewModel: IdiomListViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
) {
    val idioms = viewModel.idioms.collectAsLazyPagingItems()

    IdiomListScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onItemClick = onItemClick,
        idioms = idioms,
        onSearch = { viewModel.search(it) },
    )

}

@Composable
private fun IdiomListScreen(
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
        value = query,
        onValueChange = {
            query = it
            onSearch(query)
        },
        onSearch = { }
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
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