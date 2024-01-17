package com.hefengbao.jingmo.ui.screen.people

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.model.SimplePeopleInfo
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun PeopleIndexRoute(
    viewModel: PeopleIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (type: String, query: String) -> Unit
) {
    val recommendList by viewModel.recommendList.collectAsState(initial = emptyList())
    val searchResult by viewModel.searchResult.collectAsState(emptyList())

    PeopleIndexScreen(
        onBackClick = onBackClick,
        recommendList = recommendList,
        onSearch = { viewModel.search(it) },
        searchResult = searchResult,
        onItemClick = onItemClick
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
private fun PeopleIndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    recommendList: List<String>,
    onSearch: (String) -> Unit,
    searchResult: List<SimplePeopleInfo>,
    onItemClick: (type: String, query: String) -> Unit
) {
    val keyboard = LocalSoftwareKeyboardController.current

    SimpleScaffold(
        onBackClick = onBackClick,
        title = "人物"
    ) {
        var query by rememberSaveable {
            mutableStateOf("")
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = modifier.fillMaxWidth().padding(16.dp)
        ) {
            item(
                span = {
                    GridItemSpan(3)
                }
            ) {
                SearchBar(
                    query = query,
                    onQueryChange = { query = it },
                    onSearch = {
                        if (it.isNotEmpty()) {
                            onSearch(it)
                            keyboard?.hide()
                        }
                    },
                    active = false,
                    onActiveChange = {}
                ) {}
            }

            if (query.isNotEmpty()) {
                item(
                    span = {
                        GridItemSpan(3)
                    }
                ) {
                    Text(
                        text = "搜索列表",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = modifier.padding(16.dp, 16.dp)
                    )
                }

                items(
                    items = searchResult,
                ) { people ->
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onItemClick("id", people.id.toString())
                            }
                            .padding(16.dp, 8.dp),
                        text = people.name,
                    )
                }
            }

            item(
                span = {
                    GridItemSpan(3)
                }
            ) {
                Text(
                    text = "推荐列表",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = modifier.padding(16.dp, 16.dp)
                )
            }

            items(recommendList) { name ->
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onItemClick("name", name)
                        }
                        .padding(16.dp, 8.dp),
                    text = name,
                )
            }
        }
    }
}