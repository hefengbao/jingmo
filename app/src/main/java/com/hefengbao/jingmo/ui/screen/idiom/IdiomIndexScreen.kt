package com.hefengbao.jingmo.ui.screen.idiom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ReadMore
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.hefengbao.jingmo.data.database.model.SimpleIdiomInfo
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun IdiomIndexRoute(
    modifier: Modifier = Modifier,
    viewModel: IdiomIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    onReadMoreClick: () -> Unit,
    onBookmarksClick: () -> Unit,
) {
    val idioms = viewModel.idioms.collectAsLazyPagingItems()

    IdiomIndexScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onItemClick = onItemClick,
        idioms = idioms,
        onSearch = { viewModel.search(it) },
        onReadMoreClick = onReadMoreClick,
        onBookmarksClick = onBookmarksClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun IdiomIndexScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    idioms: LazyPagingItems<SimpleIdiomInfo>,
    onSearch: (String) -> Unit,
    onReadMoreClick: () -> Unit,
    onBookmarksClick: () -> Unit,
) {
    var query by rememberSaveable {
        mutableStateOf("")
    }

    var showSearchBar by rememberSaveable {
        mutableStateOf(false)
    }

    val keyboard = LocalSoftwareKeyboardController.current

    SimpleScaffold(
        onBackClick = onBackClick,
        title = "成语",
        actions = {
            IconButton(onClick = onBookmarksClick) {
                Icon(imageVector = Icons.Outlined.Bookmarks, contentDescription = "收藏")
            }
            IconButton(onClick = onReadMoreClick) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ReadMore, contentDescription = "阅读")
            }
            if (showSearchBar) {
                IconButton(
                    onClick = {
                        showSearchBar = false
                        query = ""
                        onSearch(query)
                    }
                ) {
                    Icon(imageVector = Icons.Default.SearchOff, contentDescription = "查找")
                }
            } else {
                IconButton(onClick = { showSearchBar = true }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "查找")
                }
            }
        }
    ) {
        if (showSearchBar) {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth(),
                state = rememberLazyListState()
            ) {
                item {
                    SearchBar(
                        modifier = modifier.padding(horizontal = 16.dp),
                        query = query,
                        onQueryChange = {
                            query = it
                            onSearch(query)
                        },
                        onSearch = {
                            onSearch(query)
                            keyboard?.hide()
                        },
                        active = false,
                        onActiveChange = {}
                    ) {}
                }
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
        } else {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth(),
                state = rememberLazyListState()
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
}