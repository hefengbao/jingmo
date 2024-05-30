package com.hefengbao.jingmo.ui.screen.chineseexpression

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.paging.compose.itemsIndexed
import com.hefengbao.jingmo.data.database.entity.ChineseExpressionEntity
import com.hefengbao.jingmo.ui.component.SimpleSearchScaffold

@Composable
fun ChineseExpressionSearchRoute(
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    viewModel: ChineseExpressionSearchViewModel = hiltViewModel(),
) {
    val expressions = viewModel.expressions.collectAsLazyPagingItems()

    ChineseExpressionSearchScreen(
        onBackClick = onBackClick,
        onSearch = { viewModel.search(it) },
        onItemClick = onItemClick,
        expressions = expressions
    )
}

@Composable
private fun ChineseExpressionSearchScreen(
    onBackClick: () -> Unit,
    onSearch: (String) -> Unit,
    onItemClick: (Int) -> Unit,
    expressions: LazyPagingItems<ChineseExpressionEntity>
) {
    var query by rememberSaveable {
        mutableStateOf("")
    }
    SimpleSearchScaffold(
        onBackClick = onBackClick,
        query = query,
        onQueryChange = { query = it },
        onSearch = onSearch
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(
                items = expressions
            ) { _: Int, entity: ChineseExpressionEntity? ->
                entity?.let {
                    Text(
                        text = it.word,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onItemClick(it.id)
                            }
                            .padding(16.dp, 8.dp)
                    )
                }
            }
        }
    }
}