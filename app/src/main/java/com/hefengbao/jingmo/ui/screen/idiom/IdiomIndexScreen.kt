package com.hefengbao.jingmo.ui.screen.idiom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ReadMore
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.model.IdiomWithBookmark
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.idiom.components.IdiomShowPanel

@Composable
fun IdiomIndexRoute(
    viewModel: IdiomIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onSearchClick: () -> Unit,
) {
    val idiom by viewModel.idiom.collectAsState(initial = null)

    IdiomIndexScreen(
        idiom = idiom,
        onBackClick = onBackClick,
        onReadMoreClick = onReadMoreClick,
        onBookmarksClick = onBookmarksClick,
        onSearchClick = onSearchClick,
        onFabClick = { viewModel.getRandomIdiom() }
    )
}

@Composable
private fun IdiomIndexScreen(
    idiom: IdiomWithBookmark?,
    onBackClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onSearchClick: () -> Unit,
    onFabClick: () -> Unit
) {
    idiom?.let { entity ->
        SimpleScaffold(
            onBackClick = onBackClick,
            title = "成语",
            actions = {
                IconButton(onClick = onBookmarksClick) {
                    Icon(imageVector = Icons.Outlined.Bookmarks, contentDescription = "收藏")
                }
                IconButton(onClick = onReadMoreClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ReadMore,
                        contentDescription = "阅读"
                    )
                }
                IconButton(onClick = onSearchClick) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "查找")
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = onFabClick) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "刷新")
                }
            }
        ) {
            IdiomShowPanel(idiom = entity)
        }
    }
}