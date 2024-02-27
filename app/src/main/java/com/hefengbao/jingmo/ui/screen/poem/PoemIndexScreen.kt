package com.hefengbao.jingmo.ui.screen.poem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun PoemIndexRoute(
    viewModel: PoemIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onAuthorClick: (String) -> Unit,
    onBookmarksClick: () -> Unit,
    onReadMoreClick: () -> Unit,
) {
    val recommendList by viewModel.recommendList.collectAsState(initial = emptyList())

    PoemIndexScreen(
        onBackClick = onBackClick,
        onAuthorClick = onAuthorClick,
        onSearchClick = onSearchClick,
        onBookmarksClick = onBookmarksClick,
        onReadMoreClick = onReadMoreClick,
        recommendList = recommendList,
    )
}

@Composable
private fun PoemIndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onAuthorClick: (String) -> Unit,
    onBookmarksClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    recommendList: List<String>,
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = "诗文",
        actions = {
            IconButton(onClick = onBookmarksClick) {
                Icon(imageVector = Icons.Outlined.Bookmarks, contentDescription = "收藏")
            }
            IconButton(onClick = onReadMoreClick) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ReadMore, contentDescription = "阅读")
            }
            IconButton(onClick = onSearchClick) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "查找")
            }
        }
    ) {
        LazyVerticalGrid(
            modifier = modifier.padding(16.dp),
            columns = GridCells.Fixed(3)
        ) {
            item(
                span = {
                    GridItemSpan(3)
                }
            ) {
                Text(
                    text = "常用推荐",
                    modifier = modifier.padding(8.dp),
                    style = MaterialTheme.typography.headlineMedium
                )
            }
            items(
                items = recommendList
            ) {
                Text(text = it, modifier = modifier
                    .clickable {
                        onAuthorClick(it)
                    }
                    .padding(8.dp))
            }
        }
    }
}