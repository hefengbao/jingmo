package com.hefengbao.jingmo.ui.screen.writing

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ReadMore
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.model.WritingWithBookmark
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.writing.components.WritingShowPanel
import kotlinx.serialization.json.Json

@Composable
fun WritingIndexRoute(
    viewModel: WritingIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onReadMoreClick: () -> Unit,
) {
    val writing by viewModel.writing.collectAsState(initial = null)

    WritingIndexScreen(
        onBackClick = onBackClick,
        onSearchClick = onSearchClick,
        onBookmarksClick = onBookmarksClick,
        onReadMoreClick = onReadMoreClick,
        writing = writing,
        setUncollect = { viewModel.setUncollect(it) },
        setCollect = { viewModel.setCollect(it) },
        onFabClick = { viewModel.getRandomWriting() },
        json = viewModel.json,
    )
}

@Composable
private fun WritingIndexScreen(
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    writing: WritingWithBookmark?,
    setUncollect: (Int) -> Unit,
    setCollect: (Int) -> Unit,
    onFabClick: () -> Unit,
    json: Json
) {
    writing?.let { entity ->
        var isCollect = writing.collectedAt != null

        SimpleScaffold(
            onBackClick = onBackClick,
            title = "诗文",
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
            bottomBar = {
                BottomAppBar(
                    floatingActionButton = {
                        FloatingActionButton(onClick = onFabClick) {
                            Icon(imageVector = Icons.Default.Refresh, contentDescription = "刷新")
                        }
                    },
                    actions = {
                        /*IconButton(
                            onClick = {
                                if (isCollect) {
                                    setUncollect(writing.id)
                                } else {
                                    setCollect(writing.id)
                                }
                                isCollect = !isCollect
                            }
                        ) {
                            if (isCollect) {
                                Icon(
                                    imageVector = Icons.Default.Bookmark,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.BookmarkBorder,
                                    contentDescription = null
                                )
                            }
                        }*/
                    }
                )
            }
        ) {
            WritingShowPanel(
                writing = entity,
                prevId = null,
                nextId = null,
                setCurrentId = {},
                json = json
            )
        }
    }
}